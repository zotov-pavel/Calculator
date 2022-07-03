class Calculator {
    int num1;
    int num2;
    NumbersType type;

    public Calculator(int num1, int num2, NumbersType type) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
    }

    public String calculate(Operation operation) throws ScannerException {
        if(type == NumbersType.ROMAN) {
            int result;
            result =  switch(operation) {
                case ADD -> num1 + num2;
                case SUBTRACT -> num1 - num2;
                case MULTIPLY -> num1 * num2;
                case DIVISION -> num1 / num2;
            };

            if(result > 0) {
                return Converter.arabicToRoman(result);
            }
            throw new ScannerException();
        }

        return switch(operation) {
            case ADD -> String.valueOf(num1 + num2);
            case SUBTRACT -> String.valueOf(num1 - num2);
            case MULTIPLY -> String.valueOf(num1 * num2);
            case DIVISION -> String.valueOf(num1 / num2);
        };
    }
}
