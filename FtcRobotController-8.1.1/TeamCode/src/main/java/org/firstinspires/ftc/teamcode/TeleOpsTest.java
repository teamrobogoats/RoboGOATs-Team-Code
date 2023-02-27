package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TeleOpsTest", group = "Driver Controlled")
public class TeleOpsTest extends LinearOpMode {

    public DcMotor leftFront;
    public DcMotor rightFront;
    public DcMotor leftBack;
    public DcMotor rightBack;
    public DcMotor arm;
    private ElapsedTime runtime = new ElapsedTime();
    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    //public DcMotor intake;
    //public DcMotor wheel;
    //public Servo claw;
    //DigitalChannel digitalChannelTop;
    //DigitalChannel digitalChannelBottom;

    public final double servo1_home = 0.0;
    public final double servo1_maxRange = 0.0;
    public final double servo2_home = 0.3;
    public final double servo2_maxRange = 0.0;
    //Servo servo;
    //public static double servoPosition = 0.0;
    CRServo servo1;
    //CRServo servo2;
    CRServo   servo2;
    //double servo1Position = 0.0;
    double servo2Position = 0.0;
    double servo1Position = 0.0;

    //double MIN_POSITION = 0, MAX_POSITION = 1;
    //float speed = 0.0f;
    double turnspeed = 0.0f;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */

    public void runOpMode() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        arm = hardwareMap.dcMotor.get("arm");
        servo1 = hardwareMap.get(CRServo.class, "servo1");
        servo2 = hardwareMap.get(CRServo.class, "servo2");

        telemetry.addData(">", "servos working");
        telemetry.update();

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //arm.getCurrentPosition();

        //servo.setPosition(servoPosition);
        waitForStart();

        //digitalChannelTop = hardwareMap.get(DigitalChannel.class, "touchTop");
        //digitalChannelTop.setMode(DigitalChannel.Mode.INPUT);

        //touch1 = hardwareMap.get(TouchSensor.class, "touch1");
        //touch2 = hardwareMap.get(TouchSensor.class, "touch2");
        //leftFront.setPower(speed);

        // Put run blocks here.
        while (opModeIsActive()) {
            double speed = 0.0;
            leftFront.setPower(speed);
            leftBack.setPower(-speed);
            rightFront.setPower(speed);
            rightBack.setPower(speed);

            if (gamepad2.dpad_down) {
                encoderDriveForwardMoveSlide(.3, 10, 10, 10, 5, true);
                telemetry.addData("Raising ", "down");
                telemetry.update();
            }
            else if (gamepad2.dpad_up) {
                encoderDriveForwardMoveSlide(.3, 10, 10, -100, 5, false);
                //arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            } else if(gamepad2.dpad_left) {
                telemetry.addData("arm ", " current power %f", arm.getPower());
                telemetry.addData("arm ",  "current pos %7d ", arm.getCurrentPosition());
                telemetry.addData("Current position",  "LF :%7d RF :%7d LB: %7d, RB :%7d",
                        leftFront.getCurrentPosition(), rightFront.getCurrentPosition(),
                        leftBack.getCurrentPosition(), rightBack.getCurrentPosition());
                telemetry.addData("Arm ", "Current mode: ", arm.getMode());
                telemetry.update();
            }
        }
    }

    public void encoderDriveForwardMoveSlide(double speed, double leftInches, double rightInches,
                                             int slideTargetPos, double timeoutS, boolean resetEncoder)
    {
        int newRightFrontTarget;
        int newLeftFrontTarget;
        int newRightBackTarget;
        int newLeftBackTarget;
        double armPower;
        boolean isGoingUp;
        isGoingUp = arm.getCurrentPosition() > slideTargetPos ?  true :  false;
        if (isGoingUp) {
            armPower = 0.4;
        }
        else{
            armPower = 0.3;
        }

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Ensure that the opmode is still active
        if (opModeIsActive()) {
            newRightFrontTarget = rightFront.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftFrontTarget = leftFront.getCurrentPosition() - (int)(leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBack.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = leftBack.getCurrentPosition() - (int)(leftInches * COUNTS_PER_INCH);
            leftFront.setTargetPosition(newLeftFrontTarget);
            leftBack.setTargetPosition(newLeftBackTarget);
            rightFront.setTargetPosition(newRightFrontTarget);
            rightBack.setTargetPosition(newRightBackTarget);
            arm.setTargetPosition(slideTargetPos);

            // Turn On RUN_TO_POSITION
            leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            leftFront.setPower(speed);
            rightFront.setPower(speed);
            leftBack.setPower(speed);
            rightBack.setPower(speed);
            arm.setPower(armPower);

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
                telemetry.addData("arm ", " current power %f", arm.getPower());
                telemetry.addData("arm ",  "current pos %7d ", arm.getCurrentPosition());
                telemetry.addData("Current position",  "LF :%7d RF :%7d LB: %7d, RB :%7d",
                        leftFront.getCurrentPosition(), rightFront.getCurrentPosition(),
                        leftBack.getCurrentPosition(), rightBack.getCurrentPosition());

                telemetry.update();
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

            if(resetEncoder == true) {
                arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        }
    }
}
