package net.violantic.totem.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

public class VectorUtil {

    public static Vector calculateVelocity(Vector from, Vector to, int heightGain, double gravity) {
        int endGain = to.getBlockY() - from.getBlockY();
        double horizDist = Math.sqrt(distanceSquared(from, to));
        double maxGain = heightGain > endGain + heightGain ? (double)heightGain : (double)(endGain + heightGain);
        double a = -horizDist * horizDist / (4.0D * maxGain);
        double c = (double)(-endGain);
        double slope = -horizDist / (2.0D * a) - Math.sqrt(horizDist * horizDist - 4.0D * a * c) / (2.0D * a);
        double vy = Math.sqrt(maxGain * gravity);
        double vh = vy / slope;
        int dx = to.getBlockX() - from.getBlockX();
        int dz = to.getBlockZ() - from.getBlockZ();
        double mag = Math.sqrt((double)(dx * dx + dz * dz));
        double dirx = (double)dx / mag;
        double dirz = (double)dz / mag;
        double vx = vh * dirx;
        double vz = vh * dirz;
        return new Vector(vx, vy, vz);
    }

    public static Vector getMidPoint(Vector v1, Vector v2) {
        return new Vector((v1.getX() + v2.getX()) / 2.0D, (v1.getY() + v2.getY()) / 2.0D, (v1.getZ() + v2.getZ()) / 2.0D);
    }

    public static Location toLocation(World world, Vector vector) {
        return new Location(world, vector.getX(), vector.getY(), vector.getZ());
    }

    private static double distanceSquared(Vector from, Vector to) {
        double dx = (double)(to.getBlockX() - from.getBlockX());
        double dz = (double)(to.getBlockZ() - from.getBlockZ());
        return dx * dx + dz * dz;
    }

    public static Vector rotateAroundAxisX(Vector v, double cos, double sin) {
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisY(Vector v, double cos, double sin) {
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static Vector rotateAroundAxisX(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double y = v.getY() * cos - v.getZ() * sin;
        double z = v.getY() * sin + v.getZ() * cos;
        return v.setY(y).setZ(z);
    }

    public static Vector rotateAroundAxisY(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos + v.getZ() * sin;
        double z = v.getX() * -sin + v.getZ() * cos;
        return v.setX(x).setZ(z);
    }

    public static Vector rotateAroundAxisZ(Vector v, double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        double x = v.getX() * cos - v.getY() * sin;
        double y = v.getX() * sin + v.getY() * cos;
        return v.setX(x).setY(y);
    }

    public static Vector rotateVector(Vector v, double angleX, double angleY, double angleZ) {
        rotateAroundAxisX(v, angleX);
        rotateAroundAxisY(v, angleY);
        rotateAroundAxisZ(v, angleZ);
        return v;
    }

    public static Vector rotateVector(Vector v, Location location) {
        return rotateVector(v, location.getYaw(), location.getPitch());
    }

    public static Vector rotateVector(Vector v, float yawDegrees, float pitchDegrees) {
        double yaw = Math.toRadians((double)(-1.0F * (yawDegrees + 90.0F)));
        double pitch = Math.toRadians((double)(-pitchDegrees));
        double cosYaw = Math.cos(yaw);
        double cosPitch = Math.cos(pitch);
        double sinYaw = Math.sin(yaw);
        double sinPitch = Math.sin(pitch);
        double initialX = v.getX();
        double initialY = v.getY();
        double x = initialX * cosPitch - initialY * sinPitch;
        double y = initialX * sinPitch + initialY * cosPitch;
        double initialZ = v.getZ();
        double z = initialZ * cosYaw - x * sinYaw;
        x = initialZ * sinYaw + x * cosYaw;
        return new Vector(x, y, z);
    }

    public static double angleToXAxis(Vector vector) {
        return Math.atan2(vector.getX(), vector.getY());
    }

}
