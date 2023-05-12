import java.util.*;

public class ExpressionTree 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a fully parenthesized arithmetic expression: ");
        String expression = input.nextLine();

        Node root = buildExpressionTree(expression);

        System.out.println("Expression tree:");
        printExpressionTree(root);
        System.out.printf("Value of root: %.2f\n", evaluateExpressionTree(root));
    }

    public static Node buildExpressionTree(String expression) 
    {
        Stack<Node> nodeStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) 
        {
            char c = expression.charAt(i);
            if (c == '(') {
                opStack.push(c);
            } 
            else if (c == ')') 
            {
                while (opStack.peek() != '(') 
                {
                    char op = opStack.pop();
                    Node right = nodeStack.pop();
                    Node left = nodeStack.pop();
                    Node node = new Node(op, left, right);
                    nodeStack.push(node);
                }
                opStack.pop(); 
            } 
            else if (c == '+' || c == '-' || c == '*' || c == '/') 
            {
                while (!opStack.isEmpty() && precedence(opStack.peek()) >= precedence(c)) 
                {
                    char op = opStack.pop();
                    Node right = nodeStack.pop();
                    Node left = nodeStack.pop();
                    Node node = new Node(op, left, right);
                    nodeStack.push(node);
                }
                opStack.push(c);
            } 
            else if (Character.isDigit(c)) 
            {
                int j = i;
                while (j < expression.length() && (Character.isDigit(expression.charAt(j)) || expression.charAt(j) == '.')) {
                    j++;
                }
                double value = Double.parseDouble(expression.substring(i, j));
                Node node = new Node(value);
                nodeStack.push(node);
                i = j - 1;
            }
        }
        while (!opStack.isEmpty()) 
        {
            char op = opStack.pop();
            Node right = nodeStack.pop();
            Node left = nodeStack.pop();
            Node node = new Node(op, left, right);
            nodeStack.push(node);
        }
        return nodeStack.pop();
    }

    public static double evaluateExpressionTree(Node root) 
    {
        if (root.isNumber()) 
        {
            return root.getValue();
        } 
        else 
        {
            char op = root.getOperator();
            double leftValue = evaluateExpressionTree(root.getLeft());
            double rightValue = evaluateExpressionTree(root.getRight());
            if (op == '+') {
                return leftValue + rightValue;
            } else if (op == '-') {
                return leftValue - rightValue;
            } else if (op == '*') {
                return leftValue * rightValue;
            } else { // op == '/'
                return leftValue / rightValue;
            }
        }
    }

    public static void printExpressionTree(Node root) 
    {
        if (root == null) 
        {
            return;
        }
        if (root.isNumber()) 
        {
            System.out.print(root.getValue());
        } 
        else 
        {
            System.out.print("(");
            printExpressionTree(root.getLeft());
            System.out.print(" " + root.getOperator() + " ");
            printExpressionTree(root.getRight());
            System.out.print(")");
        }
    }

    public static int precedence(char op) 
    {
        if (op == '+' || op == '-') 
        {
            return 1;
        } 
        else 
        { 
            return 2;
        }
    }

    public static class Node 
    {
        private char operator;
        private double value;
        private Node left;
        private Node right;

        public Node(char operator, Node left, Node right) 
        {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }

        public Node(double value) 
        {
            this.value = value;
        }

        public char getOperator() 
        {
            return operator;
        }

        public double getValue() 
        {
            return value;
        }

        public Node getLeft() 
        {
            return left;
        }

        public Node getRight() 
        {
            return right;
        }

        public boolean isNumber() 
        {
            return operator == 0;
        }
    }
}