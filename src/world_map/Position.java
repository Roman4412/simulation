package world_map;


import java.util.Objects;

public class Position {
    //TODO расчитывать расстояние чебышева в статическом методе в интерфейсе карты, который будет возвращать число.
    //Изменить поиск соседних клеток. Вместо генерации Position брать их из карты через цикл
    // вместо метода eat добавить метод attack, который будет переопределяться у наследников в зависимости от силы атаки и типа
    public int chebyshevDistance;
    public final int vertical;
    public final int horizontal;

    public Position(int vertical, int horizontal) {
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public int findChebyshevDistance(Position target) {
        chebyshevDistance = Math.max(Math.abs(vertical - target.vertical), Math.abs(horizontal - target.horizontal));
        return chebyshevDistance;
    }


    @Override
    public String toString() {
        return vertical + "," + horizontal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return vertical == position.vertical && horizontal == position.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }
}
