#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank, MoveJoystick,MoveSteering, SpeedPercent, Motor
from ev3dev2.sensor.lego import ColorSensor, InfraredSensor, GyroSensor, UltrasonicSensor
from ev3dev2.button import Button

from time import sleep

Black = 1
Red = 5
White = 6
Brown = 7

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

        self.lastColor1 = None
        self.lastColor2 = None
        self.lastColor3 = None
        self.node = [(0,0)]
        # self.direction = None
        self.directionStr = ['top', 'right', 'bottom', 'left']


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
            self.direction = (self.direction - 1) % 4
        else:
            motor_turns_deg = -motor_turns_deg
            self.direction = (self.direction + 1) % 4
        
        self.left_motor.run_forever(speed_sp=motor_turns_deg)
        self.right_motor.run_forever(speed_sp=-motor_turns_deg)

        begin = self.gy.value()
        while(abs(begin - self.gy.value()) < 85 and not self.btn.any()) :
            sleep(0.01)
        
        self.arrete2()
        

    def fini(self):
        exit()


    def run(self):
        self.lastColor1 = self.cl.color
        self.lastColor2 = self.cl.color
        self.lastColor3 = self.cl.color
        self.direction = 0
        x = 0
        y = 0
        i = 0
        while not self.btn.any() :    # exit loop when any button pressed
            i = i + 1
            distance = self.us.value()/10 
            color = self.cl.color
            self.beginAngle = self.gy.value()
            direction = None

            print("----------------------------------")
            # print("tour : " + str(i))
            # # print("distance: " + str(distance))
            # print("color1 : " + str(self.lastColor1))
            # print("color2 : " + str(self.lastColor2))
            # print("color : " + str(color))
            print("gyro: " + str(self.beginAngle))

            shouldTurn = False

            if (distance < 10):
                shouldTurn = True
                self.node.append((x,y))

            if (color != self.lastColor2):
                self.lastColor1 = self.lastColor2
                self.lastColor2 = self.lastColor3
                self.lastColor3 = color

            if (self.lastColor3 == self.lastColor1
                and self.lastColor3 != self.lastColor2
                and self.lastColor2 == Black):
                shouldTurn = True
                direction = "left"
                self.lastColor1 = self.lastColor3
                self.lastColor2 = self.lastColor3
            elif (self.lastColor3 != self.lastColor1
                and self.lastColor3 != self.lastColor2
                and self.lastColor2 == Black):
                shouldTurn = True
                # direction = "right"
                self.lastColor1 = self.lastColor3
                self.lastColor2 = self.lastColor3

            if (self.lastColor3 == White and self.lastColor1 == Red):
                direction = "left"
            elif (self.lastColor3 == White and self.lastColor1 == Red):
                direction = "right"

            if (shouldTurn == True):
                # self.turn_90(direction)
                pass
            else:
                # self.drive()
                pass
            # sleep(5)

        self.arrete()


robot = Robot(OUTPUT_A, OUTPUT_D)

robot.run()
