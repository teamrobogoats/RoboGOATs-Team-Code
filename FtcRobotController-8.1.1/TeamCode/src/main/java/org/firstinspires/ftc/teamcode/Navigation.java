package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Navigation")
public class Navigation extends LinearOpMode {

    DcMotor leftBack;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor rightFront;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        leftBack = hardwareMap.get(DcMotor.class, "LeftBack");
        leftFront = hardwareMap.get(DcMotor.class, "LeftFront");
        rightBack = hardwareMap.get(DcMotor.class, "RightBack");
        rightFront = hardwareMap.get(DcMotor.class, "RightFront");

        // Put initialization blocks here.
        waitForStart();

        while (opModeIsActive()) {
            // Put run blocks here.

            if (gamepad1.dpad_down) {
                leftBack.setPower(0.5);
                leftFront.setPower(0.5);
                rightBack.setPower(-0.5);
                rightFront.setPower(-0.5);
            }
            else if (gamepad1.dpad_up) {
                leftBack.setPower(-0.5);
                leftFront.setPower(-0.5);
                rightBack.setPower(0.5);
                rightFront.setPower(0.5);
            }
            else if (gamepad1.dpad_right) {
                leftBack.setPower(0.5);
                leftFront.setPower(-0.5);
                rightBack.setPower(0.5);
                rightFront.setPower(-0.5);
            }
            else if (gamepad1.dpad_left) {
                leftBack.setPower(-0.5);
                leftFront.setPower(0.5);
                rightBack.setPower(-0.5);
                rightFront.setPower(0.5);
            }

            else {
                leftBack.setPower(0);
                leftFront.setPower(0);
                rightBack.setPower(0);
                rightFront.setPower(0);
            }
                // Put loop blocks here.
                telemetry.speak("hello", null, null);
                telemetry.update();

        }
    }
}