#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank, MoveJoystick,MoveSteering, SpeedPercent, Motor
from ev3dev2.sensor.lego import ColorSensor, InfraredSensor, GyroSensor, UltrasonicSensor
from ev3dev2.button import Button

from time import sleep

# RED = 20
# BLANCH = 100
# NOIR = 0

# ROTATION = 90 * 4


class Robot:
    def __init__(self, output_a, output_d):
        self.right_motor = LargeMotor(output_a)
        self.left_motor = LargeMotor(output_d)

        self.btn = Button()

        self.cl = ColorSensor()

        self.us = UltrasonicSensor()
        self.us.mode='US-DIST-CM'

        self.gy = GyroSensor()
        self.gy.mode='GYRO-ANG'

        self.defaultSpeed = 450

        self.right_sp = 450
        self.left_sp = 450

        self.lastColor = None


    def demarrer(self, vitessA, vitessD):
        self.move.on(vitessA, vitessD)
        self.speedA = vitessA
        self.speedD = vitessD


    def arrete(self):
        self.left_motor.stop(stop_action="coast")
        self.right_motor.stop(stop_action="coast")


    def arrete2(self):
        self.left_motor.stop(stop_action="brake")
        self.right_motor.stop(stop_action="brake")


    def drive(self):
        self.left_motor.run_forever(speed_sp=-self.left_sp)
        self.right_motor.run_forever(speed_sp=-self.right_sp)
    

    def drive_straight(self):
        self.left_motor.run_forever(speed_sp=-self.left_sp)
        self.right_motor.run_forever(speed_sp=-self.right_sp)
        sleep(0.6)
        self.arrete2()


    def turn_90(self, direction):
        motor_turns_deg = 360
        if direction == 'left':
            motor_turns_deg = motor_turns_deg  # May require some tuning depending on your surface!
        else:
            motor_turns_deg = -motor_turns_deg
        
        self.left_motor.run_forever(speed_sp=motor_turns_deg)
        self.right_motor.run_forever(speed_sp=-motor_turns_deg)

        begin = self.gy.value()
        while(abs(begin - self.gy.value()) < 85 and not self.btn.any()) :
            sleep(0.01)
        
        self.arrete2()
        

    def fini(self):
        exit()


    def run(self):
        while not self.btn.any():    # exit loop when any button pressed
            distance = self.us.value()/10 
            color = self.cl.color

            print("distance: " + str(distance))
            print("color: " + str(color))

            shouldTurn = False

            if (distance < 10):
                shouldTurn = True

            # if (color != self.lastColor):
            #     shouldTurn = True
            #     self.lastColor = color

            if (shouldTurn == True):
                self.turn_90("left")
            else:
                self.drive()

        self.arrete()


robot = Robot(OUTPUT_A, OUTPUT_D)

robot.run()
