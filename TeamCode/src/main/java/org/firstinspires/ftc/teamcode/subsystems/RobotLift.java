package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDController;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;

public class RobotLift extends Subsystem {
    public static final RobotLift  INSTANCE = new RobotLift();
    private RobotLift() { }
    private static MotorEx robot_lift_motor;
    public PIDController controller = new PIDController(new PIDCoefficients(0.005, 0.0, 0.0)); //TODO: Tune Lift Motor PID
    public String name = "motor0B";

    public Command lift_to_ascent() {
        return new RunToPosition(robot_lift_motor,
                0.0, controller, this);
    }
    public Command lift_reset() {
        return new RunToPosition(robot_lift_motor,
                0.0, controller, this);
    }

    @Override
    public void initialize() {
        robot_lift_motor = new MotorEx(name);
        robot_lift_motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }
}
