package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;
//import java.util.Random;

@Autonomous(name="AutoRightPark", group="Red")
public class AutoRightPark extends LinearOpMode {
    //1.5 seconds of spinning at 0.75 = 2 ft.
    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;
    public DcMotor arm;
    CRServo servo1;
    CRServo   servo2;
    //public DcMotor claw;
    //public DcMotor wheel;
    DigitalChannel digitalChannelTop;
    DigitalChannel digitalChannelBottom;

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 312 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     SPEED_RATE_KP           = 0.0003 ;
    static final double     WHEEL_DIAMETER_INCHES   = 4 ;     // For figuring circumference
    static final double APPROACH_SPEED = 0.5;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     DRIVE_SPEED_SLOW             = 0.2;
    static final double     REVERSE_SPEED           =0.2;
    static final double     TURN_SPEED              = 0.2;
    double servo2Position = 0.0;
    double servo1Position = 0.0;

    private static final String[] LABELS = {
            "RedGoat",
            "RedCrown",
            "RedRoboticArm",
            "Goat",
            "Crown",
            "RoboticArm"
    };

    public void runOpMode() {

        /* Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        arm = hardwareMap.dcMotor.get("arm");
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        servo2 = hardwareMap.get(CRServo.class, "servo2");

        telemetry.addData("Autonomous Mode Status", "Ready to Run");
        telemetry.update();

        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Encoders", "Reset",
                leftFront.getCurrentPosition(),
                rightFront.getCurrentPosition(),
                leftBack.getCurrentPosition(),
                rightBack.getCurrentPosition());
        telemetry.update();




        waitForStart();
        if (opModeIsActive()) {
            runtime.reset();

        }
        runtime.reset();


        moveArmSlightlyS();
        encoderDriveShuffleLeft(DRIVE_SPEED, 20, 20, 8.0);
        moveArmSlightlyDownS();

        /*
        encoderDriveTurnLeft(DRIVE_SPEED, -10, -10, 8.0);
        encoderDriveMoveForward(DRIVE_SPEED, -1.5, -1.5, 8.0);
        encoderDriveTurnLeft(DRIVE_SPEED, -19, -19, 8.0);
        encoderDriveShuffleLeft(DRIVE_SPEED, 2, 2, 8.0);
        moveArmSlightlyNoS();
        moveArmSlightlyOpen();
        encoderDriveMoveForward(DRIVE_SPEED, 18, 18, 8.0);
        holdCone();
        moveArmSlightlyNoS();
        encoderDriveMoveForward(DRIVE_SPEED, -6, -6, 8.0);*/


        //moveArmSlightly();
        //encoderDriveShuffleRight(DRIVE_SPEED, 20, 20, 8.0);


        //moveArmDownSlightly();

        //holdCone(8.0);
        //encoderDriveShuffleRight(DRIVE_SPEED, -15, -15, 8.0);
        //moveArmAboveGround();
/*
        encoderDriveMoveForward(DRIVE_SPEED, 44, 44, 8.0);
        encoderDriveTurnLeft(DRIVE_SPEED, 10, 10, 8.0);
        encoderDriveMoveForward(DRIVE_SPEED, 3, 3, 8.0);
        moveArmTop();
        encoderDriveMoveForward(DRIVE_SPEED, 1, 1, 8.0);
        deliverCone(8.0);
        encoderDriveMoveForward(DRIVE_SPEED, -3, -3, 8.0);
        moveArmDown(8.0);*/
        //encoderDriveMoveForward(DRIVE_SPEED, -3, -3, 8.0);
        //encoderDriveTurnLeft(DRIVE_SPEED, -10, -10, 8.0);

        //encoderDriveMoveForward(DRIVE_SPEED, 12, 12, 8.0);
        //this one moveArmTop();
        //encoderDriveTurnLeft(DRIVE_SPEED, -20, -20, 8.0);
        //encoderDriveShuffleRight(DRIVE_SPEED, 3, 3, 8.0);
        //encoderDriveMoveForward(DRIVE_SPEED, 5, 5, 8.0);
        //this one deliverCone(8.0);
        //encoderDriveMoveForward(DRIVE_SPEED, -5, -5, 8.0);
/*
        encoderDriveMoveForward(DRIVE_SPEED, -5, -5, 8.0);
        encoderDriveTurnLeft(DRIVE_SPEED, -20, -20, 8.0);
        encoderDriveMoveForward(DRIVE_SPEED, 25, 25, 8.0);
        moveArmDownSlightly(8.0);
        holdCone(8.0);
        moveArmTop();
        encoderDriveMoveForward(DRIVE_SPEED, -25, -25, 8.0);
        encoderDriveTurnLeft(DRIVE_SPEED, 20, 20, 8.0);
        encoderDriveMoveForward(DRIVE_SPEED, 5, 5, 8.0);
        deliverCone(8.0);
        encoderDriveMoveForward(DRIVE_SPEED, -5, -5, 8.0);*/

        /*
        if (coneSide == "RoboticArm") {
            encoderDriveShuffleLeft(DRIVE_SPEED, 10, 10, 8.0);
        }
        else if (coneSide == "Crown") {
            encoderDriveShuffleRight(DRIVE_SPEED, 10, 10, 8.0);
        }
        else {
            encoderDriveShuffleRight(DRIVE_SPEED, 20, 20, 8.0);
        }*/

        //this one moveArmDown(8.0);

        telemetry.addData("Moving", "");
        telemetry.update();
        runtime.reset();
    }






    public void encoderDriveTurnLeft(double speed, double leftInches,
                                     double rightInches, double timeoutS)
    {
        int newRightFrontTarget;
        int newLeftFrontTarget;
        int newRightBackTarget;
        int newLeftBackTarget;

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newRightFrontTarget = rightFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftFrontTarget = leftFront.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = leftBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            leftFront.setTargetPosition(newLeftFrontTarget);
            leftBack.setTargetPosition(newLeftBackTarget);
            rightFront.setTargetPosition(newRightFrontTarget);
            rightBack.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFront.setPower(-speed);
            rightFront.setPower(-speed);
            leftBack.setPower(speed);
            rightBack.setPower(speed);
            arm.setPower(-0.05);
            //wheel.setPower(1.0);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                arm.setPower(-0.05);
                leftFront.getCurrentPosition();
                leftBack.getCurrentPosition();
                rightFront.getCurrentPosition();
                rightBack.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void encoderDriveShuffleLeft(double speed, double leftInches,
                                        double rightInches, double timeoutS)
    {
        int newRightFrontTarget;
        int newLeftFrontTarget;
        int newRightBackTarget;
        int newLeftBackTarget;

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newRightFrontTarget = rightFront.getCurrentPosition() - (int)(rightInches * COUNTS_PER_INCH);
            newLeftFrontTarget = leftFront.getCurrentPosition() - (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = leftBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            leftFront.setTargetPosition(newLeftFrontTarget);
            leftBack.setTargetPosition(newLeftBackTarget);
            rightFront.setTargetPosition(newRightFrontTarget);
            rightBack.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFront.setPower(speed);
            rightFront.setPower(speed);
            leftBack.setPower(-speed);
            rightBack.setPower(-speed);
            arm.setPower(-0.05);
            //wheel.setPower(1.0);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                arm.setPower(-0.05);
                leftFront.getCurrentPosition();
                leftBack.getCurrentPosition();
                rightFront.getCurrentPosition();
                rightBack.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void encoderDriveShuffleRight(double speed, double leftInches,
                                         double rightInches, double timeoutS)
    {
        int newRightFrontTarget;
        int newLeftFrontTarget;
        int newRightBackTarget;
        int newLeftBackTarget;

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newRightFrontTarget = rightFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftFrontTarget = leftFront.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBack.getCurrentPosition() - (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = leftBack.getCurrentPosition() - (int)(leftInches * COUNTS_PER_INCH);
            leftFront.setTargetPosition(newLeftFrontTarget);
            leftBack.setTargetPosition(newLeftBackTarget);
            rightFront.setTargetPosition(newRightFrontTarget);
            rightBack.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFront.setPower(-speed);
            rightFront.setPower(-speed);
            leftBack.setPower(speed);
            rightBack.setPower(speed);
            arm.setPower(-0.05);
            //wheel.setPower(1.0);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                arm.setPower(-0.05);
                leftFront.getCurrentPosition();
                leftBack.getCurrentPosition();
                rightFront.getCurrentPosition();
                rightBack.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
    public void moveArmSlightlyS()
    {
        if (opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 1.5) {
                arm.setPower(-0.3);
                servo1.setPower(0.2);
                servo2.setPower(-0.2);
                telemetry.addData("Moving", "arm up slightly");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }

    }
    public void moveArmSlightlyNoS()
    {
        if (opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 1.5) {
                arm.setPower(-0.25);
                telemetry.addData("Moving", "arm up slightly");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }
    }
    public void moveArmSlightlyDownS()
    {
        if (opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 1) {
                arm.setPower(0.3);
                telemetry.addData("Moving", "arm up slightly");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }

    }
    public void moveArmSlightlyOpen()
    {
        if (opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 1.3) {
                servo1.setPower(-0.1);
                servo2.setPower(0.06);
                sleep(50);
                telemetry.addData("Moving", "arm up slightly");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }
    }
    public void moveArmDown()
    {
        if (opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 3) {
                arm.setPower(0.2);
                //servo1.setPower(0.2);
                //servo2.setPower(-0.2);
                telemetry.addData("Moving", "arm down");
                telemetry.update();
            }
            //arm.setPower(-0.05);
        }

    }
    public void moveArmDownSlightly()
    {
        if (opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 2) {
                arm.setPower(0.2);
                //servo1.setPower(0.2);
                //servo2.setPower(-0.2);
                telemetry.addData("Moving", "arm down slightly");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }

    }
    public void moveArmTop()
    {
        if (opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 8) {
                arm.setPower(-0.4);
                //servo1.setPower(0.2);
                //servo2.setPower(-0.2);
                telemetry.addData("Moving", "arm Reached Top");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }

    }
    public void moveArmAboveGround()
    {
        if(opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 1) {
                arm.setPower(-0.5);
                telemetry.addData("Moving", "arm up");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }

    }

    public void moveArmAboveGroundFromTopPosition()
    {
        if(opModeIsActive()) {
            runtime.reset();
            while (runtime.seconds() < 2) {
                arm.setPower(-0.1);
                telemetry.addData("Moving", "arm down to above ground");
                telemetry.update();
            }
            arm.setPower(-0.05);
        }

    }

    public void open()
    {
        if(opModeIsActive()){
            runtime.reset();
            while (runtime.seconds() < 1.0) {
                servo1.setPower(-0.15);
                servo2.setPower(0.15);
                telemetry.addData("Moving", "claw out");
                telemetry.update();
                sleep(50);
            }
        }

    }
    public void holdCone()
    {
        if(opModeIsActive()){
            runtime.reset();
            //Servo.Direction direction = Servo.Direction.FORWARD;
            servo1.setPower(0.2);
            servo2.setPower(-0.2);

            telemetry.addData("Moving", "claw in");
            telemetry.update();
            sleep(50);
        }

    }
    public void deliverCone(double timeoutS)
    {
        if(opModeIsActive()){
            runtime.reset();
            //Servo.Direction direction = Servo.Direction.FORWARD;
            servo1.setPower(-0.1);
            servo2.setPower(0.1);

            telemetry.addData("Moving", "claw out");
            telemetry.update();

        }

    }
    private void setMotorPower(DcMotor motor, double distance )
    {
        //int error = (int)(rightInches * COUNTS_PER_INCH)
        /*
        motor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        boolean onTarget = false;

        while (!onTarget)
        {
            int error = target - motor.getCurrentPosition();
            motor.setPower(Range.clip(error*Kp, -1.0, 1.0));
            onTarget = Math.abs(error) <= tolerance;
        }
        motor.setPower(0.0);

         */
    }

    public void encoderDriveMoveForward(double speed, double leftInches,
                                        double rightInches, double timeoutS)
    {
        int newRightFrontTarget;
        int newLeftFrontTarget;
        int newRightBackTarget;
        int newLeftBackTarget;

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller

            newRightFrontTarget = rightFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftFrontTarget = leftFront.getCurrentPosition() - (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = leftBack.getCurrentPosition() - (int)(leftInches * COUNTS_PER_INCH);
            leftFront.setTargetPosition(newLeftFrontTarget);
            leftBack.setTargetPosition(newLeftBackTarget);
            rightFront.setTargetPosition(newRightFrontTarget);
            rightBack.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            leftFront.setPower(speed);
            rightFront.setPower(speed);
            leftBack.setPower(speed);
            rightBack.setPower(speed);
            arm.setPower(-0.05);
            //wheel.setPower(1.0);

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                arm.setPower(-0.05);
                leftFront.getCurrentPosition();
                leftBack.getCurrentPosition();
                rightFront.getCurrentPosition();
                rightBack.getCurrentPosition();
                telemetry.update();
            }

            // Stop all motion;
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }


    private void encoderDriveMoveSlide(double leftInches, double rightInches, int slideTargetPosition,
                                       boolean isGoingUp, boolean resetEncoder, double timeoutS) {
        int newRightFrontTarget;
        int newLeftFrontTarget;
        int newRightBackTarget;
        int newLeftBackTarget;
        double armPower;
        double speed;
        arm.setTargetPosition(slideTargetPosition);

        if (isGoingUp) {
            armPower = 0.4;
        }
        else{
            armPower = 0.2;
        }

        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller
            newRightFrontTarget = rightFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftFrontTarget = leftFront.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget =leftBack.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);

            leftFront.setTargetPosition(newLeftFrontTarget);
            leftBack.setTargetPosition(newLeftBackTarget);
            rightFront.setTargetPosition(newRightFrontTarget);
            rightBack.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFront.isBusy() && leftBack.isBusy() && rightFront.isBusy() && rightBack.isBusy()) ||
                    (arm.isBusy()))
            {

                int targetToCurrent = Math.abs(Math.abs(newLeftFrontTarget) - Math.abs(leftFront.getCurrentPosition())) ;
                speed = Range.clip(targetToCurrent * SPEED_RATE_KP + APPROACH_SPEED, -1.0, 1.0);

                leftFront.setPower(Math.abs(speed));
                rightFront.setPower(Math.abs(speed));
                leftBack.setPower(Math.abs(speed));
                rightBack.setPower(Math.abs(speed));

                arm.setPower(armPower);

                // Display it for the driver.
                telemetry.addData("Drive Speed: %f", speed);
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftFrontTarget,  newRightFrontTarget, newLeftBackTarget, newRightBackTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                telemetry.update();
                if(!arm.isBusy()) {
                    arm.setPower(0.05);
                }

                if(!leftFront.isBusy() || !leftBack.isBusy() || !rightFront.isBusy() || !rightBack.isBusy()) {
                    leftFront.setPower(0);
                    leftBack.setPower(0);
                    rightFront.setPower(0);
                    rightBack.setPower(0);
                }
                idle();
            }

            // Stop all motion;
            leftFront.setPower(0);
            leftBack.setPower(0);
            rightFront.setPower(0);
            rightBack.setPower(0);
            arm.setPower(0.05);

            // Turn off RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            if(slideTargetPosition == 0 || resetEncoder) {
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }
    }
}