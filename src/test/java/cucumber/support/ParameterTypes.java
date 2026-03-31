package cucumber.support;

import domain.piece.Side;
import domain.position.Position;
import io.cucumber.java.ParameterType;

public class ParameterTypes {
    @ParameterType("\"(초|한)\"")
    public Side side(String sideName) {
        return sideName.equals("초") ? Side.CHO : Side.HAN;
    }

    @ParameterType("\"(\\d+), (\\d+)\"")
    public Position position(String row, String col) {
        return Position.of(Integer.parseInt(row), Integer.parseInt(col));
    }
}
