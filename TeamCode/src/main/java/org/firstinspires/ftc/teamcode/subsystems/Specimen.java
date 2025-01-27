package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.OpModeData;
import com.rowanmcalpin.nextftc.ftc.hardware.ServoToPosition;

public class Specimen extends Subsystem {
    public static final Specimen INSTANCE = new Specimen();
    private Specimen() { }
    public Servo specimen_servo;

    public String name = "servo2";

    public double specimen_hold_position = 0.485;
    public double specimen_open_position = 0.68;

    public Command hold() {
        return new ServoToPosition(specimen_servo,
                specimen_hold_position,
                this);
    }
    public Command open() {
        return new ServoToPosition(specimen_servo,
                specimen_open_position,
                this);
    }

    @Override
    public void initialize() {
        specimen_servo = OpModeData.INSTANCE.getHardwareMap().get(Servo.class, name);
        specimen_servo.setDirection(Servo.Direction.FORWARD);
    }
}

