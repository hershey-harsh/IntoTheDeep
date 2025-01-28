package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.utility.InstantCommand;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Bucket extends Subsystem {
    public static final Bucket INSTANCE = new Bucket();
    private Bucket() { }
    public Servo bucket_servo;

    public String name = "servo0";

    public double bucket_servo_drop_position = 0.36;
    public double bucket_servo_ready_position = 0.5;

    public Command drop() {
        return new ServoToPosition(bucket_servo,
                bucket_servo_drop_position,
                this);
    }
    public Command ready() {
        return new ServoToPosition(bucket_servo,
                bucket_servo_ready_position,
                this);
    }
    public Command enable() {
        return new InstantCommand(() -> {
            bucket_servo.getController().pwmEnable();
            return null;
        });
    }
    public Command disable() {
        return new InstantCommand(() -> {
            bucket_servo.getController().pwmDisable();
            return null;
        });
    }

    @Override
    public void initialize() {
        bucket_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
        bucket_servo.setDirection(Servo.Direction.FORWARD);
        bucket_servo.getController().pwmDisable();
    }
}

