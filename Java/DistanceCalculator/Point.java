public class Point 
{
    private double x;
    private double y;

    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getDistance(Point p)
    {
        double xDist = Math.pow((p.getX() - x), 2);
        double yDist = Math.pow((p.getY() - y), 2);
        double distance = (double)Math.round((Math.sqrt(xDist + yDist) * 100)) / 100;
        return distance;
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}