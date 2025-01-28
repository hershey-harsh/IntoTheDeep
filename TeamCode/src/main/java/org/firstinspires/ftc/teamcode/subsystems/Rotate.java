package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Rotate extends Subsystem {
    public static final Rotate INSTANCE = new Rotate();
    private Rotate() { }
    public Servo rotate_servo;

    public String name = "servo2B";

    public double min_position = 0;
    public double default_position = 0.5;
    public double max_position = 1;

    private double current_position = default_position;

    private double clamp(double position) {
        return Math.max(min_position, Math.min(max_position, position));
    }
    public Command rotate_to(double position) {
        double constrainedPosition = clamp(position);

        if (Tilt.INSTANCE.tilt_servo.getPosition() < 0.3) {
            current_position = constrainedPosition;
            return new ServoToPosition(rotate_servo,
                    constrainedPosition,
                    this);
        } else {
            return rotate_to(default_position);
        }
    }

    public Command rotate_max() {
        return rotate_to(max_position);
    }
    public Command rotate_default() {
        return rotate_to(default_position);
    }
    public Command rotate_min() {
        return rotate_to(min_position);
    }

    public Command rotate_incremental() {
        return rotate_to(current_position + 0.01);
    }
    public Command rotate_decremental() {
        return rotate_to(current_position - 0.01);
        }

    public double get_current_position() {
        return current_position;
    }

    @Override
    public void initialize() {
        rotate_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
        rotate_servo.setDirection(Servo.Direction.FORWARD);
        rotate_servo.setPosition(default_position); // Initialize at default position
    }
}