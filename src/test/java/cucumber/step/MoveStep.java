package cucumber.step;

import cucumber.TestContext;
import domain.board.Board;
import domain.board.Placement;
import domain.piece.Piece;
import domain.piece.Side;
import domain.position.Position;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveStep {

    private final TestContext context;

    public MoveStep() {
        this.context = new TestContext();
    }

    @Given("장기판이 초기화 되어 있다.")
    public void 장기판이_초기화되어_있다() {
        context.initBoard();
        Board board = context.getBoard();
        // 기본 배치(마상상마)로 초기화
        board.placePieces(Side.CHO, Placement.INNER_ELEPHANT);
        board.placePieces(Side.HAN, Placement.INNER_ELEPHANT);
    }

    @Given("{side} 진영에서 {position} 위치의 {string}을 {position} 위치로 이동시켰다.")
    @Given("{side} 진영에서 {position} 위치의 {string}를 {position} 위치로 이동시켰다.")
    @When("{side} 진영에서 {position} 위치의 {string}을 {position} 위치로 이동시키면")
    @When("{side} 진영에서 {position} 위치의 {string}를 {position} 위치로 이동시키면")
    public void 기물을_이동시킨다(Side side, Position from, String pieceName, Position to) {
        context.clearException();
        try {
            context.getBoard().move(from, to, side);
        } catch (IllegalArgumentException e) {
            context.setLastException(e);
        }
    }

    @Then("{position} 위치에 {string}이 있어야 한다.")
    @Then("{position} 위치에 {string}가 있어야 한다.")
    public void 위치에_기물이_있어야_한다(Position position, String pieceName) {
        Piece piece = context.getBoard().findBy(position);
        assertThat(piece).isNotNull();
        assertThat(piece.getTypeName()).isEqualTo(mapToDomainName(pieceName));
    }

    @And("{position} 위치는 비어 있어야 한다.")
    public void 위치는_비어_있어야_한다(Position position) {
        Piece piece = context.getBoard().findBy(position);
        assertThat(piece).isNull();
    }

    @Then("{position} 위치에 {side} 진영의 {string}이 있어야 한다.")
    public void 위치에_진영의_기물이_있어야_한다(Position position, Side side, String pieceName) {
        Piece piece = context.getBoard().findBy(position);
        assertThat(piece).isNotNull();
        assertThat(piece.getSide()).isEqualTo(side);
        assertThat(piece.getTypeName()).isEqualTo(mapToDomainName(pieceName));
    }

    @Then("이동에 실패한다.")
    public void 이동에_실패한다() {
        assertThat(context.getLastException()).isInstanceOf(IllegalArgumentException.class);
    }

    // =============== private method =======================

    private String mapToDomainName(String pieceName) {
        if (pieceName.equals("졸") || pieceName.equals("병")) return "병";
        return pieceName;
    }
}
