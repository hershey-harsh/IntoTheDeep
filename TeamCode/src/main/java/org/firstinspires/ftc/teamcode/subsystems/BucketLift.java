package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDController;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.core.control.coefficients.PIDCoefficients;

@Config
public class BucketLift extends Subsystem {
    public static final BucketLift INSTANCE = new BucketLift();
    private BucketLift() { }

    private static MotorEx bucket_lift_motor;
    private static DigitalChannel digital;

    public PIDFController liftController = new PIDFController(new PIDCoefficients(0.005, 0.0, 0.0)); //TODO: Tune Lift Motor PID

    public String name = "motor1B";
    public String lift_switch_name = "digital1";

    public double bucket_min_position = 0;
    public double bucket_max_position = 2770;
    public double specimen_grab_position = 461;
    public double specimen_hang_position = 1392;

    public boolean is_digital_pressed() {
        return !digital.getState();
    }

    public Command lift_to_basket() {
        resetTiltToDefault();
        return new RunToPosition(bucket_lift_motor,
                    bucket_max_position,
                    liftController,
                    this);
    }
    public Command lift_to_specimen() {
        resetTiltToDefault();
        return new RunToPosition(bucket_lift_motor,
                    specimen_grab_position,
                    liftController,
                    this);
    }
    public Command lift_reset() {
        bucket_lift_motor.resetEncoder();
        return new RunToPosition(bucket_lift_motor,
                specimen_grab_position,
                liftController,
                this);
    }
    public Command lift_to_min() {
        resetTiltToDefault();
        return new RunToPosition(bucket_lift_motor,
                bucket_min_position,
                liftController,
                this);
    }
    public Command lift_to_bar() {
        resetTiltToDefault();
        return new RunToPosition(bucket_lift_motor,
                    specimen_hang_position,
                    liftController,
                    this);
    }

    private void resetTiltToDefault() {
        Tilt.INSTANCE.tilt_default();
    }

    @Override
    public void initialize() {
        bucket_lift_motor = new MotorEx(name);
        bucket_lift_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        digital = OpModeData.INSTANCE.getHardwareMap().get(DigitalChannel.class, lift_switch_name);
        digital.setMode(DigitalChannel.Mode.INPUT);
    }
    @Override
    public void periodic() {
        bucket_lift_motor.resetEncoder();

        if (!is_digital_pressed()) {
            lift_reset();
        }
    }

}
