package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;

public class BucketLift extends Subsystem {
    public static final BucketLift  INSTANCE = new BucketLift();
    private BucketLift() { }
    private static MotorEx bucket_lift_motor;

    public PIDController controller = new PIDController(new PIDCoefficients(0.005, 0.0, 0.0)); //TODO: Tune Lift Motor PID
    public String name = "motor1B";

    public double bucket_position = 2770;
    public double specimen_grab_position = 461;
    public double specimen_hang_position = 1392;

    public Command lift_to_basket() {
        return new RunToPosition(bucket_lift_motor,
                bucket_position, controller, this);
    }
    public Command lift_to_grab() {
        return new RunToPosition(bucket_lift_motor,
                specimen_grab_position, controller, this);
    }
    public Command lift_to_bar() {
        return new RunToPosition(bucket_lift_motor,
                specimen_hang_position, controller, this);
    }

    @Override
    public void initialize() {
        bucket_lift_motor = new MotorEx(name);
        bucket_lift_motor.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
