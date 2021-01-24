package Test;

import com.arkanoid.Ball;
import com.arkanoid.Brick;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BallTest {

    @Nested
    class bounceOffPaddle {
        @Test
        void leftEdge_shouldBeStronglyLeftBounced() {
            //given
            double paddleXPosition = 100;
            Ball ball = new Ball(100,480,2,2);

            //when
            ball.bounceOffPaddle(paddleXPosition);

            //then
            assertEquals(-2,ball.getX_Axis_Speed());
            assertEquals(-2,ball.getY_Axis_Speed());
        }

        @Test
        void left_shouldBeLeftBounced() {
            //given
            double paddleXPosition = 100;
            Ball ball = new Ball(111,480,2,2);

            //when
            ball.bounceOffPaddle(paddleXPosition);

            //then
            assertEquals(-1,ball.getX_Axis_Speed());
            assertEquals(-2,ball.getY_Axis_Speed());
        }

        @Test
        void middle_shouldBeWeaklyBounced() {
            //given
            double paddleXPosition = 100;
            Ball ball = new Ball(134,480,2,2);

            //when
            ball.bounceOffPaddle(paddleXPosition);

            //then
            assertEquals(2,ball.getX_Axis_Speed());
            assertEquals(-1,ball.getY_Axis_Speed());
        }

        @Test
        void right_shouldBeRightBounced() {
            //given
            double paddleXPosition = 100;
            Ball ball = new Ball(138,480,2,2);

            //when
            ball.bounceOffPaddle(paddleXPosition);

            //then
            assertEquals(1,ball.getX_Axis_Speed());
            assertEquals(-2,ball.getY_Axis_Speed());
        }

        @Test
        void rightEdge_shouldBeStronglyRightBounced() {
            //given
            double paddleXPosition = 100;
            Ball ball = new Ball(170,480,2,2);

            //when
            ball.bounceOffPaddle(paddleXPosition);

            //then
            assertEquals(2,ball.getX_Axis_Speed());
            assertEquals(-2,ball.getY_Axis_Speed());
        }
    }

    @Nested
    class bounceOffBrick {
        @Test
        void leftEdge_shouldBeWeaklyLefBounced() {
            //given
            Brick brick = new Brick(100,100,0); //brick size 60px x 20px
            Ball ball = new Ball(95,105,2,-2);
            //when
            ball.bounceOffBrick(brick);
            //then
            assertEquals(-1,ball.getX_Axis_Speed());
            assertEquals(-2,ball.getY_Axis_Speed());
        }

        @Test
        void rightEdge_shouldBeWeaklyRightBounced() {
            //given
            Brick brick = new Brick(100,100,0); //brick size 60px x 20px
            Ball ball = new Ball(160,105,-2,-2);
            //when
            ball.bounceOffBrick(brick);
            //then
            assertEquals(1,ball.getX_Axis_Speed());
            assertEquals(-2,ball.getY_Axis_Speed());
        }

        @Test
        void TopOrBottom_shouldBeReflected() {
            //given
            Brick brick = new Brick(100,100,0); //brick size 60px x 20px
            Ball ball = new Ball(130,120,2,-2);
            //when
            ball.bounceOffBrick(brick);
            //then
            assertEquals(2,ball.getY_Axis_Speed());
        }


    }
}