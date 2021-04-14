package RoboRallySB3M.Network.Data;

/**
 * Type for lasers to be used for storing location
 */
public class LaserData implements PayloadData {
    public String laserType;
    public int x;
    public int y;

    /**
     * @param laserType laserH || laserV || laser
     * @param x coordinate
     * @param y coordinate
     */
    public LaserData(String laserType, int x, int y) {
        this.laserType = laserType;
        this.x = x;
        this.y = y;
    }

    /**
     * @param laserType laserH || laserV || laser
     * @param x coordinate
     * @param y coordinate
     * @return A new laser
     */
    public static LaserData newLaser(String laserType, int x, int y) {
        return new LaserData(laserType, x, y);
    }
}
