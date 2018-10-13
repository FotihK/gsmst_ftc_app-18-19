package org.firstinspires.ftc.team3819;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by 200462069 on 9/12/2017.
 */

public class RockerBogieHardware {

    private HardwareMap map = null;

    public DcMotor  left = null, right = null, lift = null, intake = null;    //DC Motors

    private static final int       CPR = 1120;                                 //encoder counts per revolution
    private static final double    DIAMETER = 4;                               //encoded drive wheel diameter (in)
    private static final double    GEARING = 1;
    public static final double     CPI = (CPR * GEARING) / (DIAMETER * 3.14);
    public static final double     CPF = CPI * 12;

    public RockerBogieHardware(HardwareMap map){
        this.map = map;
    }

    public void init() {

        left = map.get(DcMotor.class, "left");
        right = map.get(DcMotor.class, "right");
        lift = map.get(DcMotor.class, "lift");
        intake = map.get(DcMotor.class, "intake");


        left.setDirection(DcMotorSimple.Direction.FORWARD);
        right.setDirection(DcMotorSimple.Direction.REVERSE);
        lift.setDirection(DcMotorSimple.Direction.FORWARD);


        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void drive(int num) {
        left.setPower(num);
        right.setPower(num);
    }

    public void drive(Gamepad gp) {
        float turn = gp.right_stick_x / 4;
        left.setPower(gp.left_stick_y + turn);
        right.setPower(gp.left_stick_y - turn);
    }

    public void liftUp() {
        lift.setTargetPosition(0);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setPower(25);
    }

    public void liftDown() {

        lift.setTargetPosition(300);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setPower(-25);
    }

    public void stop() {
        drive(0);
    }

    public boolean driveIsBusy(){
        return left.isBusy() || right.isBusy();
    }

    public void resetEnc(){
        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }



}
