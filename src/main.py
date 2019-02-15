#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank, MoveJoystick,MoveSteering, SpeedPercent
from ev3dev2.sensor import INPUT_1
from ev3dev2.sensor.lego import TouchSensor
from ev3dev2.led import Leds
import time


class Robot:
    def __init__(self, output_a, output_d):
        self.moteurA = output_a
        self.moteurD = output_d
        self.move = MoveSteering(self.moteurA, self.moteurD)
        self.speedA = 0
        self.speedD = 0

    def demarrer(self, vitessA, vitessD):
        self.move.on(vitessA, vitessD)
        self.speedA = vitessA
        self.speedD = vitessD

    def tourner(self, direction):
        self.arrete()
        self.move.on_for_degrees(-40, SpeedPercent(-40), direction)
        self.demarrer(self.speedA, self.speedD)

    def arrete(self):
        self.move.stop()


robot = Robot(OUTPUT_A, OUTPUT_D)

robot.demarrer(-40, -40)
robot.tourner(90)

time.sleep(5)

robot.arrete()
