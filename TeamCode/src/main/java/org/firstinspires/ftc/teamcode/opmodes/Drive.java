package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

import org.firstinspires.ftc.teamcode.routines.Tbucket;
import org.firstinspires.ftc.teamcode.subsystems.BucketLift;
import org.firstinspires.ftc.teamcode.subsystems.IntakeBlock;
import org.firstinspires.ftc.teamcode.subsystems.RobotLift;
import org.firstinspires.ftc.teamcode.subsystems.Rotate;
import org.firstinspires.ftc.teamcode.subsystems.Slide;
import org.firstinspires.ftc.teamcode.subsystems.Specimen;
import org.firstinspires.ftc.teamcode.subsystems.Tilt;

@TeleOp(name = "Competion Drive")
class Drive extends NextFTCOpMode {

    public Drive() {
        super(BucketLift.INSTANCE, IntakeBlock.INSTANCE, RobotLift.INSTANCE, Rotate.INSTANCE, Slide.INSTANCE, Specimen.INSTANCE, Tilt.INSTANCE);
    }

    public String front_left_motor_name = "motor0";
    public String front_right_motor_name = "motor1";
    public String rear_left_motor_name = "motor2";
    public String rear_right_motor_name = "motor3";

    public MotorEx frontLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;

    public MotorEx[] motors;

    public Command driverControlled;

    @Override
    public void onInit() {
        frontLeftMotor = new MotorEx(front_left_motor_name);
        backLeftMotor = new MotorEx(rear_left_motor_name);
        backRightMotor = new MotorEx(rear_right_motor_name);
        frontRightMotor = new MotorEx(front_right_motor_name);

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        motors = new MotorEx[] {frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor};
    }
    @Override
    public void onStartButtonPressed() {
        driverControlled = new MecanumDriverControlled(motors, gamepadManager.getGamepad1());
        driverControlled.invoke();

        gamepadManager.getGamepad2().getY().setReleasedCommand(Tbucket.INSTANCE::bucket_lift);

        gamepadManager.getGamepad2().getA().setReleasedCommand(Tbucket.INSTANCE::bucket_drop);
    }
}