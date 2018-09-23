public class PointCPX 
{

	private char typeCoord;
	private double xOrRho;
	private double yOrTheta;

	public PointCPX(char type, double xOrRho, double yOrTheta) 
	{
		if(type != 'C' || type != 'P')
			throw new IllegalArgumentException();
		this.xOrRho = xOrRho;
		this.yOrTheta = yOrTheta;
		typeCoord = type;
	}

	public double getX()
	{
		if(typeCoord == 'C')
			return xOrRho;
		else
			return (Math.cos(Math.toRadians(yOrTheta)) * xOrRho);
	}

	public double getY()
	{
		if(typeCoord == 'C')
			return yOrTheta;
		else
			return (Math.sin(Math.toRadians(yOrTheta)) * xOrRho);
	}

	public double getRho()
	{
		if(typeCoord == 'P')
			return xOrRho;
		else
			return (Math.sqrt(Math.pow(xOrRho, 2) + Math.pow(yOrTheta, 2)));
	}

	public double getTheta()
	{
		if(typeCoord == 'P')
			return yOrTheta;
		else
			return Math.toDegrees(Math.atan2(yOrTheta, xOrRho));
	}


	public void convertStorageToPolar()
	{
		if(typeCoord != 'P')
		{
			double temp = getRho();
			double yOrTheta = getTheta();
			xOrRho = temp;

			typeCoord = 'P';
		}		
	}

	public void convertStorageToCartesian()
	{
		if(typeCoord != 'C')
		{
			double temp = getX();
			yOrTheta = getY();
			xOrRho = temp;

			typeCoord = 'C';
		}
	}

	public double getDistance(PointCPX pointB)
	{
		double deltaX = getX() - pointB.getX();
		double deltaY = getY() - pointB.getY();

		return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
	}

	public PointCPX rotatePoint(double rotation)
	{
		double radRotation = Math.toRadians(rotation);
		double X = getX();
		double Y = getY();

		return new PointCPX('C',
      		(Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y),
      		(Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
	}

	public String toString()
	{
		return "Stored as " + 
			(typeCoord == 'C' 
			? "Cartesian  (" + getX() + "," + getY() + ")" 
			: "Polar [" + getRho() + "," + getTheta() + "]") + "\n";
	}
	
}