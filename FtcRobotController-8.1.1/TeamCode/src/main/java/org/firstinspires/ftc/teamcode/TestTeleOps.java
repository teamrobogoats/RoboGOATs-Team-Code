package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "test2 Laasya")
public class TestTeleOps extends LinearOpMode {

    DcMotor leftBack;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFront;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");

        // Put initialization blocks here.
        waitForStart();

        while (opModeIsActive()) {
            // Put run blocks here.
            if (gamepad1.dpad_left) {
                leftBack.setPower(0.5);
                leftFront.setPower(-0.5);
                rightBack.setPower(0.5);
                rightFront.setPower(-0.5);
                // Put loop blocks here.
                telemetry.speak("hello", null, null);
                telemetry.update();
            }
        }
    }
}
