package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.BoardEntity;
import inhatc.inhatcbaekjoon.domain.BoardForm;
import inhatc.inhatcbaekjoon.domain.Category;
import inhatc.inhatcbaekjoon.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/board")
    public String BoardList(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestParam(defaultValue = "ALL") String category,
            Model model) {
        List<BoardEntity> boards = boardService.findByValue(category);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("boards", boards);
        return "/board/boardMain";
    }

    @GetMapping("/board/writing")
    public String createBoard(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("BoardFrom", new BoardForm());
        return "/board/boardWriting";
    }

    @PostMapping("/board/writing")
    public String createBoard(@Valid BoardForm boardForm, BindingResult result) {
        BoardEntity boardEntity = new BoardEntity(boardForm.getTitle(), boardForm.getContent(), boardForm.getCategory());
        boardService.join(boardEntity);
        return "redirect:/board";
    }

    @GetMapping("/board/{id}")
    public String viewBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long id, Model model
    ) {
        Optional<BoardEntity> board = boardService.findById(id);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("board", board.get());
        return "/board/viewBoard";
    }

    @GetMapping("/board/modify/{id}")
    public String modifyBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long id, Model model
    ) {
        Optional<BoardEntity> board = boardService.findById(id);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("board", board.get());
        return "/board/modifyBoard";
    }

    @PutMapping("/board/modify/{id}")
    public String modifyBoard(@PathVariable Long id,
                              @RequestParam String title,
                              @RequestParam String content,
                              @RequestParam Category category)
    {
        Optional<BoardEntity> boardEntity = boardService.findById(id);
        BoardEntity board = boardEntity.get();
        board.modifyBoardEntity(title,content,category);
        boardService.join(board);
        return "redirect:/board";
    }

    @DeleteMapping("/board/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/board";
    }
}
