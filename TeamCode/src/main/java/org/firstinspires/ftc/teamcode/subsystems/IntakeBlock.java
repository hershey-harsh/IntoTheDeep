package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.MultipleServosToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

import java.util.List;

public class IntakeBlock extends Subsystem {
    public static final IntakeBlock INSTANCE = new IntakeBlock();
    private IntakeBlock() { }
    public Servo intake_wheel_left;
    public Servo intake_wheel_right;
    List<Servo> intake_servos;

    public String name_one = "servo3B";
    public String name_two = "servo1B";

    public double intake_pause = 0.5;
    public double intake_on = 0.0;
    public double outake_on = 0.5;

    public Command intake_block() {
        return new MultipleServosToPosition(intake_servos,
                intake_on,
                this);
    }
    public Command intake_pause() {
        return new MultipleServosToPosition(intake_servos,
                intake_pause,
                this);
    }
    public Command outtake_block() {
        return new MultipleServosToPosition(intake_servos,
                outake_on,
                this);
    }

    @Override
    public void initialize() {
        intake_wheel_left = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name_one);
        intake_wheel_left.setDirection(Servo.Direction.FORWARD);

        intake_wheel_right = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name_two);
        intake_wheel_right.setDirection(Servo.Direction.REVERSE);

        intake_servos = List.of(intake_wheel_left, intake_wheel_right);
    }
}
