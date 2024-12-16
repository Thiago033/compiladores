class StackMachine:
    def __init__(self):
        self.stack = []

    def execute(self, input_file):
        try:
            with open(input_file, 'r') as file:
                instructions = file.readlines()

            # process each instruction
            for instruction in instructions:
                self.process(instruction.strip())

            # final result on top of the stack
            if len(self.stack) == 1:
                print("Result:", self.stack[-1])
            else:
                print("Error: Stack is not empty after execution. Contents:", self.stack)

        except FileNotFoundError:
            print(f"Error: File '{input_file}' not found.")
        except Exception as e:
            print("Error during execution:", str(e))

    def process(self, instruction):
        if instruction.startswith("PUSH"):
            # PUSH value: push a number to the stack
            _, value = instruction.split()
            self.stack.append(int(value))
        elif instruction == "SUM":
            # SUM: adds the top two values on the stack
            b = self.stack.pop()
            a = self.stack.pop()
            self.stack.append(a + b)
        elif instruction == "SUB":
            # SUB: subtracts the top value from the second value
            b = self.stack.pop()
            a = self.stack.pop()
            self.stack.append(a - b)
        elif instruction == "MULT":
            # MULT: multiplies the top two values on the stack
            b = self.stack.pop()
            a = self.stack.pop()
            self.stack.append(a * b)
        elif instruction == "DIV":
            # DIV: divides the second value by the top value
            b = self.stack.pop()
            a = self.stack.pop()
            if b == 0:
                raise Exception("ERROR: division by zero.")
            self.stack.append(a // b) # division
        elif instruction == "PRINT":
            # PRINT: prints the top value on the stack
            print("Top of the stack:", self.stack[-1])
        else:
            raise Exception(f"ERROR: unknown instruction: '{instruction}'")


# main
if __name__ == "__main__":
    import sys

    if len(sys.argv) != 2:
        print("Usage: python main.py file.txt")
    else:
        machine = StackMachine()
        machine.execute(sys.argv[1])
