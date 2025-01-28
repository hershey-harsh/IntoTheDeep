package org.firstinspires.ftc.teamcode.routines;

import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.command.groups.SequentialGroup;

import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.BucketLift;

public class Tbucket {
    public static Tbucket INSTANCE = new Tbucket();
    private Tbucket() { }

    public Command bucket_lift() {
        return new SequentialGroup(
                BucketLift.INSTANCE.lift_to_basket(),
                Bucket.INSTANCE.enable(),
                Bucket.INSTANCE.ready()
        );
    }
    public Command bucket_drop() {
        return new SequentialGroup(
                Bucket.INSTANCE.enable(),
                Bucket.INSTANCE.drop(),
                Bucket.INSTANCE.disable(),
                BucketLift.INSTANCE.lift_to_min()
        );
    }
}
