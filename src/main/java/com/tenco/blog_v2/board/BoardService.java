package com.tenco.blog_v2.board;

import com.tenco.blog_v2.User.User;
import com.tenco.blog_v2.common.errors.Exception403;
import com.tenco.blog_v2.common.errors.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardJPARepository boardJPARepository;

    /**
     * 게시글 생성
     */
    public void createBoard(BoardDTO.SaveDTO reqDTO, User sessionUser) {
        // 요청 DTO를 엔티티로 변환하여 저장합니다.
        boardJPARepository.save(reqDTO.toEntity(sessionUser));
    }

    /**
     * 게시글 id로 조회 서비스
     *
     */
    public Board getBoard(int boardId) {

        Board board = boardJPARepository
                .findById(boardId)
                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없당게요"));
        return board;
    }

    /**
     * 게시글 상세보기 서비스, 게시글 주인 여부 판별
     */
    public  Board getBoardDetail(int boardId, User sessionUser){
        // 전략 2번
        Board board = boardJPARepository
                .findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        // 전략을 1번 JPA가 객체간에 관계를 통해 직접 쿼리를 만들고 가지고 있다.
//        Board board = boardJPARepository
//                .findById(boardId)
//                .orElseThrow(()-> new Exception404("게시글을 찾을 수 없당게요"));
        // 현재 사용자가 게시글을 작성했는지 여부 판별
        boolean isBoardOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId().equals(board.getUser().getId())){
                isBoardOwner = true;
            }
        }
        // 코드추가
        // 내가 작성한 댓글인가를 구현 해야한다.
        board.getReplies().forEach( (reply) -> {
            boolean isReplayOwner = false;
            if(sessionUser != null){
                if(sessionUser.getId().equals(reply.getUser().getId())){
                    isReplayOwner = true;
                }
            }
            // 객체에서만 존재하는 필드 - 리플 객체 엔티티 상태값 변경 처리
            reply.setReplyOwner(isReplayOwner);
        } );


        board.setBoardOwner(isBoardOwner);
        return board;
    }


    /**
     * 게시글 삭제 서비스
     */
    public void deleteBoard(int boardId, int sessionUserId) {
        // 1.
        Board board = boardJPARepository.findById(boardId).orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        // 2. 권한 처리 - 현재 사용자가 게시글 주인이 맞는가?
        if(sessionUserId != board.getUser().getId()) {
            throw new Exception403("게시글을 삭제할 권한이 없습니다");
        }

        // 3. 게시글 삭제 하기
        boardJPARepository.deleteById(boardId);
    }


    /**
     * 게시글 수정 서비스
     */
    public void updateBoard(int boardId, int sessionUser, BoardDTO.UpdateDTO reqDTO) {
        Board board = boardJPARepository.findById(boardId).orElseThrow(()->new Exception404("게시글이 없습니다."));

        if(sessionUser != board.getUser().getId()) {
            throw new Exception403("게시글 수정 권한 없음");
        }
    }

    /**
     * 모든 게시글 조회 서비스
     */
    public List<Board> getAllBoards() {
        // 게시글을 ID 기준으로 내림차순으로 정렬해서 조회해라
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return boardJPARepository.findAll();
    }
}
