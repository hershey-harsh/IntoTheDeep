package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import java.util.List;

public class Slide extends Subsystem {
    public static final Slide INSTANCE = new Slide();
    private Slide() { }
    public Servo slide_left;
    public Servo slide_right;
    List<Servo> slide_servos;

    public String name_one = "servo3";
    public String name_two = "servo4";

    public Command slide_out() {
        return new MultipleServosToPosition(slide_servos,
                0,
                this);
    }
    public Command slide_in() {
        return new MultipleServosToPosition(slide_servos,
                1,
                this);
    }

    public Command slide_incremental() {
        return new MultipleServosToPosition(slide_servos,
                slide_servos.get(0).getPosition() + 0.01,
                this);
    }
    public Command slide_decremental() {
        return new MultipleServosToPosition(slide_servos,
                slide_servos.get(0).getPosition() - 0.01,
                this);
    }

    @Override
    public void initialize() {
        slide_left = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name_one);
        slide_left.setDirection(Servo.Direction.FORWARD);

        slide_right = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name_two);
        slide_right.setDirection(Servo.Direction.REVERSE);

        slide_servos = List.of(slide_left, slide_right);
    }
}

//TODO: minServoLeftSlide: 0.559 maxServoLeftSlide: 0.737 minServoRightSlide: 0.559 maxServoRightSlide: 0.737