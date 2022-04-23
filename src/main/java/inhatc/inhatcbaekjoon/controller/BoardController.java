package inhatc.inhatcbaekjoon.controller;

import inhatc.inhatcbaekjoon.config.auth.PrincipalDetails;
import inhatc.inhatcbaekjoon.domain.BoardEntity;
import inhatc.inhatcbaekjoon.domain.BoardForm;
import inhatc.inhatcbaekjoon.domain.Category;
import inhatc.inhatcbaekjoon.repository.BoardSearch;
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
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("")
    public String BoardList(
            @ModelAttribute("boardSearch") BoardSearch boardSearch,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            Model model) {
        List<BoardEntity> boards = boardService.findByValue(boardSearch);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("boards", boards);
        return "board/boardMain";
    }

    @GetMapping("/writing")
    public String createBoard(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("BoardFrom", new BoardForm());
        return "board/boardWriting";
    }

    @PostMapping("/writing")
    public String createBoard(@Valid BoardForm boardForm, BindingResult result) {
        BoardEntity boardEntity = new BoardEntity(boardForm.getTitle(), boardForm.getContent(), boardForm.getCategory());
        boardService.join(boardEntity);
        return "redirect:/board";
    }

    @GetMapping("/{id}")
    public String viewBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long id, Model model
    ) {
        Optional<BoardEntity> board = boardService.findById(id);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("board", board.get());
        return "board/viewBoard";
    }

    @GetMapping("/modify/{id}")
    public String modifyBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @PathVariable Long id, Model model
    ) {
        Optional<BoardEntity> board = boardService.findById(id);
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("board", board.get());
        return "board/modifyBoard";
    }

    @PutMapping("/modify/{id}")
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

    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/board";
    }
}
