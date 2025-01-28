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
    public double default_position = 0.52;
    public double bucket_position = 0.64;
    public double min_position = 0.2;

    private double current_position = default_position;

    private double clamp(double position) {
        return Math.max(min_position, Math.min(max_position, position));
    }

    public Command tilt_to(double position) {
        double constrainedPosition = clamp(position);

        if (Rotate.INSTANCE.rotate_servo != null) {
            if (constrainedPosition >= 0.3 && current_position < 0.3) {
                Rotate.INSTANCE.rotate_default();
            }
        }

        current_position = constrainedPosition;
        return new ServoToPosition(tilt_servo,
                constrainedPosition,
                this);
    }

    public Command tilt_min() {
        return tilt_to(min_position);
    }
    public Command tilt_default() {
        return tilt_to(default_position);
    }
    public Command tilt_bucket() {
        return tilt_to(bucket_position);
    }
    public Command tilt_max() {
        return tilt_to(max_position);
    }

    public Command tilt_incremental() {
        return tilt_to(current_position + 0.01);
    }
    public Command tilt_decremental() {
        return tilt_to(current_position - 0.01);
    }

    public double get_current_position() {
        return current_position;
    }

    @Override
    public void initialize() {
        tilt_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
        tilt_servo.setDirection(Servo.Direction.FORWARD);
        tilt_servo.setPosition(default_position); // Initialize at default position
    }
}
