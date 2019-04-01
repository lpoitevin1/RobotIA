#!/usr/bin/env python3
from ev3dev2.motor import LargeMotor, OUTPUT_A, OUTPUT_D, SpeedPercent, MoveTank, MoveJoystick,MoveSteering, SpeedPercent, Motor
from ev3dev2.sensor.lego import ColorSensor, InfraredSensor, GyroSensor
from ev3dev2.button import Button

from time import sleep

RED = 20
BLANCH = 100
NOIR = 0

ROTATION = 90 * 4

# class Robot:
#     def __init__(self, output_a, output_d):
#         self.moteurA = output_a
#         self.moteurD = output_d
#         self.move = MoveTank(self.moteurA, self.moteurD)

#         assert self.moteurA.connected
#         assert self.moteurD.connected

#         self.btn = Button()

#         self.cl = ColorSensor()
#         self.ir = InfraredSensor()
#         self.ir.mode = 'IR-PROX'

#         self.speedA = 0
#         self.speedD = 0


#     def demarrer(self, vitessA, vitessD):
#         self.move.on(vitessA, vitessD)
#         self.speedA = vitessA
#         self.speedD = vitessD


#     def tourner(self, direction):
#         self.arrete()
#         self.move.on_for_degrees(-40, SpeedPercent(-40), direction)
#         self.demarrer(self.speedA, self.speedD)


#     def arrete(self):
#         self.move.stop()


#     def run(self):
#         while not self.btn.any():    # exit loop when any button pressed
#             distance = self.ir.value()
#             print(self.cl.color)
#             print(distance)
#             print(9)

#             # if we are over the black line (weak reflection)
#             if self.cl.color == 5: # red 
#                 # medium turn right
#                 self.move.on(left_speed=-50, right_speed=-50)
            
#             if self.cl.color == 1:  # black
#                 # medium turn left
#                 self.move.on(left_speed=-50, right_speed=0)

#             if self.cl.color == 6: # white
#                 self.move.on(left_speed=0 , right_speed=-50)
#             sleep(1) # wait for 0.1 seconds


class Robot:
    def __init__(self, output_a, output_d):
        self.right_motor = LargeMotor(output_a)
        self.left_motor = LargeMotor(output_d)

        # assert self.right_motor.connected
        # assert self.left_motor.connected

        self.btn = Button()

        self.cl = ColorSensor()
        self.ir = InfraredSensor()
        self.ir.mode = 'IR-PROX'

        self.gy = GyroSensor()
        self.gy.mode='GYRO-ANG'

        self.right_sp = 450
        self.left_sp = 450


    def demarrer(self, vitessA, vitessD):
        self.move.on(vitessA, vitessD)
        self.speedA = vitessA
        self.speedD = vitessD


    # def tourner(self, direction):


    def arrete(self):
        self.left_motor.stop(stop_action="coast")
        self.right_motor.stop(stop_action="coast")


    def drive(self):
        self.left_motor.run_forever(speed_sp=-self.left_sp)
        self.right_motor.run_forever(speed_sp=-self.right_sp)
    
    def drive_straight(self):
        self.left_motor.run_forever(speed_sp=-self.left_sp)
        self.right_motor.run_forever(speed_sp=-self.right_sp)
        sleep(0.6)
        self.left_motor.stop(stop_action="brake")
        self.right_motor.stop(stop_action="brake")

    def turn_90(self, direction):
        # motor_turns_deg = 486
        # if direction == 'left':
        #     motor_turns_deg = motor_turns_deg  # May require some tuning depending on your surface!
        # else:
        #     motor_turns_deg = -motor_turns_deg
        # self.left_motor.run_to_rel_pos(position_sp=motor_turns_deg, speed_sp=400)
        # self.right_motor.run_to_rel_pos(position_sp=-motor_turns_deg, speed_sp=400)
        # # Note, that there is no delay using the commands above, so we must wait
        # self.left_motor.wait_while(Motor.STATE_RUNNING)  # Wait for the turn to finish
        # self.right_motor.wait_while(Motor.STATE_RUNNING)  # Wait for the turn to finish
        self.left_motor.run_forever(speed_sp=360)
        self.right_motor.run_forever(speed_sp=-360)
        begin = self.gy.value()
        
        while(abs(begin - self.gy.value()) < 90 and not self.btn.any()) :
            sleep(0.01)
        

    def fini(self):
        exit()


    def run(self):
        while not self.btn.any():    # exit loop when any button pressed
            distance = self.ir.value()
            # print(self.cl.color)
            print("distance: " + str(distance))
            if (distance < (30)) :
                self.turn_90("right")
            else:
                self.drive_straight()

            # if we are over the black line (weak reflection)
            # if self.cl.color == ColorSensor.COLOR_RED: # red 
            #     # medium turn right
            #     # self.move.on(left_speed=-50, right_speed=-50)
            #     self.turn_90("right")
            
            # if self.cl.color == ColorSensor.COLOR_BLACK:  # black
            #     # medium turn left
            #     # self.move.on(left_speed=-50, right_speed=0)
            #     self.turn_90("left")

            # if self.cl.color == ColorSensor.COLOR_WHITE: # white
            #     # self.move.on(left_speed=0 , right_speed=-50)
            #     self.drive_straight()
            # sleep(1) # wait for 0.1 seconds


robot = Robot(OUTPUT_A, OUTPUT_D)

robot.run()
