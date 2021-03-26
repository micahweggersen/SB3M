package RoboRallySB3M.GameObjects.Data;

import java.util.Objects;

public class LaserData {
    public String laserType;
    public int x;
    public int y;

    public LaserData(String laserType, int x, int y) {
        this.laserType = laserType;
        this.x = x;
        this.y = y;
    }

    public static LaserData newLaser(String laserType, int x, int y) {
        return new LaserData(laserType, x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaserData laserData = (LaserData) o;
        return x == laserData.x && y == laserData.y && Objects.equals(laserType, laserData.laserType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(laserType, x, y);
    }
}
