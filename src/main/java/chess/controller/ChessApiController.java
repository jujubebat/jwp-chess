package chess.controller;

import chess.dto.request.BoardRequestDto;
import chess.dto.request.PiecesRequestDto;
import chess.dto.response.PiecesResponseDto;
import chess.dto.response.RoomsResponseDto;
import chess.dto.response.ScoreResponseDto;
import chess.exception.PieceMoveException;
import chess.service.ChessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChessApiController {

    private final ChessService chessService;

    public ChessApiController(ChessService chessService) {
        this.chessService = chessService;
    }

    @GetMapping(value = "/rooms")
    public RoomsResponseDto getRooms() {
        return chessService.getRooms();
    }

    @PostMapping(value = "/pieces")
    public PiecesResponseDto postPieces(@RequestBody PiecesRequestDto piecesRequestDto) {
        return chessService.postPieces(piecesRequestDto);
    }

    @PutMapping(value = "/pieces")
    public PiecesResponseDto putPieces(@RequestBody BoardRequestDto boardRequestDto) {
        return chessService.putPieces(boardRequestDto);
    }

    @GetMapping(value = "/score")
    public ScoreResponseDto getScore(@RequestParam("roomId") int roomId,
        @RequestParam("color") String colorName) {
        return chessService.getScore(roomId, colorName);
    }

    @ExceptionHandler(PieceMoveException.class)
    public ResponseEntity<String> exceptionHandle(PieceMoveException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
