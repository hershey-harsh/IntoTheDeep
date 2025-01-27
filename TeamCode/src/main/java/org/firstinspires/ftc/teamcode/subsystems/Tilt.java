package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Tilt extends Subsystem {
    public static final Tilt INSTANCE = new Tilt();
    private Tilt() { }
    public Servo tilt_servo;

    public String name = "servo5B";

    public double max_position = 0.85;
    public double min_position = 0.2;

    private double clamp(double position) {
        return Math.max(min_position, Math.min(max_position, position));
    }
    public Command tilt_to(double position) {
        double constrainedPosition = clamp(position);
        return new ServoToPosition(tilt_servo,
                constrainedPosition,
                this);
    }

    public Command tilt_max() {
        return tilt_to(max_position);
    }
    public Command tilt_min() {
        return tilt_to(min_position);
    }

    @Override
    public void initialize() {
        tilt_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
        tilt_servo.setDirection(Servo.Direction.FORWARD);
    }
}
