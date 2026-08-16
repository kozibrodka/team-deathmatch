

package net.kozibrodka.deathmatch.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;

public class GameBox {
    private static List cache = new ArrayList();
    private static int cacheCount = 0;
    public int minX;
    public int minY;
    public int minZ;
    public int maxX;
    public int maxY;
    public int maxZ;

    public static GameBox create(Vec3i pos1, Vec3i pos2) {
        return new GameBox(pos1, pos2);
    }

    private GameBox(Vec3i pos1, Vec3i pos2){
        this.minX = Math.min(pos1.x, pos2.x);
        this.minY = Math.min(pos1.y, pos2.y);
        this.minZ = Math.min(pos1.z, pos2.z);
        this.maxX = Math.max(pos1.x, pos2.x);
        this.maxY = Math.max(pos1.y, pos2.y);
        this.maxZ = Math.max(pos1.z, pos2.z);
    }

    public Vec3i randomPosInXZPlane(Random random) {

        int x = minX + random.nextInt(maxX - minX + 1);
        int z = minZ + random.nextInt(maxZ - minZ + 1);
        int y = maxY; // zakładam, że Y jest wspólne dla obu punktów

        return new Vec3i(x, y, z);
    }

    public boolean containsIn2D(double mobX, double mobZ) {
        return mobX >= this.minX && mobX <= this.maxX && mobZ >= this.minZ && mobZ <= this.maxZ;
    }

    public static GameBox create(int x1, int y1, int z1, int x2, int y2, int z2) {
        return new GameBox(x1, y1, z1, x2, y2, z2);
    }

    @Environment(EnvType.CLIENT)
    public static void clearCache() {
        cache.clear();
        cacheCount = 0;
    }

    public static void resetCacheCount() {
        cacheCount = 0;
    }

    public static GameBox createCached(int x1, int y1, int z1, int x2, int y2, int z2) {
        if (cacheCount >= cache.size()) {
            cache.add(create(0, 0, 0, 0, 0, 0));
        }

        return ((GameBox)cache.get(cacheCount++)).set(x1, y1, z1, x2, y2, z2);
    }

    private GameBox(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.minX = x1;
        this.minY = y1;
        this.minZ = z1;
        this.maxX = x2;
        this.maxY = y2;
        this.maxZ = z2;
    }

    public GameBox set(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.minX = x1;
        this.minY = y1;
        this.minZ = z1;
        this.maxX = x2;
        this.maxY = y2;
        this.maxZ = z2;
        return this;
    }

    public GameBox stretch(int x, int y, int z) {
        int var7 = this.minX;
        int var9 = this.minY;
        int var11 = this.minZ;
        int var13 = this.maxX;
        int var15 = this.maxY;
        int var17 = this.maxZ;
        if (x < (double)0.0F) {
            var7 += x;
        }

        if (x > (double)0.0F) {
            var13 += x;
        }

        if (y < (double)0.0F) {
            var9 += y;
        }

        if (y > (double)0.0F) {
            var15 += y;
        }

        if (z < (double)0.0F) {
            var11 += z;
        }

        if (z > (double)0.0F) {
            var17 += z;
        }

        return createCached(var7, var9, var11, var13, var15, var17);
    }

    public GameBox expand(int x, int y, int z) {
        int var7 = this.minX - x;
        int var9 = this.minY - y;
        int var11 = this.minZ - z;
        int var13 = this.maxX + x;
        int var15 = this.maxY + y;
        int var17 = this.maxZ + z;
        return createCached(var7, var9, var11, var13, var15, var17);
    }

    public GameBox offset(int x, int y, int z) {
        return createCached(this.minX + x, this.minY + y, this.minZ + z, this.maxX + x, this.maxY + y, this.maxZ + z);
    }

    public double getXOffset(GameBox box, double x) {
        if (!(box.maxY <= this.minY) && !(box.minY >= this.maxY)) {
            if (!(box.maxZ <= this.minZ) && !(box.minZ >= this.maxZ)) {
                if (x > (double)0.0F && box.maxX <= this.minX) {
                    double var4 = this.minX - box.maxX;
                    if (var4 < x) {
                        x = var4;
                    }
                }

                if (x < (double)0.0F && box.minX >= this.maxX) {
                    double var6 = this.maxX - box.minX;
                    if (var6 > x) {
                        x = var6;
                    }
                }

                return x;
            } else {
                return x;
            }
        } else {
            return x;
        }
    }

    public double getYOffset(GameBox box, double y) {
        if (!(box.maxX <= this.minX) && !(box.minX >= this.maxX)) {
            if (!(box.maxZ <= this.minZ) && !(box.minZ >= this.maxZ)) {
                if (y > (double)0.0F && box.maxY <= this.minY) {
                    double var4 = this.minY - box.maxY;
                    if (var4 < y) {
                        y = var4;
                    }
                }

                if (y < (double)0.0F && box.minY >= this.maxY) {
                    double var6 = this.maxY - box.minY;
                    if (var6 > y) {
                        y = var6;
                    }
                }

                return y;
            } else {
                return y;
            }
        } else {
            return y;
        }
    }

    public double getZOffset(GameBox box, double z) {
        if (!(box.maxX <= this.minX) && !(box.minX >= this.maxX)) {
            if (!(box.maxY <= this.minY) && !(box.minY >= this.maxY)) {
                if (z > (double)0.0F && box.maxZ <= this.minZ) {
                    double var4 = this.minZ - box.maxZ;
                    if (var4 < z) {
                        z = var4;
                    }
                }

                if (z < (double)0.0F && box.minZ >= this.maxZ) {
                    double var6 = this.maxZ - box.minZ;
                    if (var6 > z) {
                        z = var6;
                    }
                }

                return z;
            } else {
                return z;
            }
        } else {
            return z;
        }
    }

    public boolean intersects(GameBox box) {
        if (!(box.maxX <= this.minX) && !(box.minX >= this.maxX)) {
            if (!(box.maxY <= this.minY) && !(box.minY >= this.maxY)) {
                return !(box.maxZ <= this.minZ) && !(box.minZ >= this.maxZ);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public GameBox translate(double x, double y, double z) {
        this.minX += x;
        this.minY += y;
        this.minZ += z;
        this.maxX += x;
        this.maxY += y;
        this.maxZ += z;
        return this;
    }

    public boolean contains(Vec3d pos) {
        if (!(pos.x <= this.minX) && !(pos.x >= this.maxX)) {
            if (!(pos.y <= this.minY) && !(pos.y >= this.maxY)) {
                return !(pos.z <= this.minZ) && !(pos.z >= this.maxZ);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Environment(EnvType.CLIENT)
    public double getAverageSideLength() {
        double var1 = this.maxX - this.minX;
        double var3 = this.maxY - this.minY;
        double var5 = this.maxZ - this.minZ;
        return (var1 + var3 + var5) / (double)3.0F;
    }

    public GameBox contract(int x, int y, int z) {
        int var7 = this.minX + x;
        int var9 = this.minY + y;
        int var11 = this.minZ + z;
        int var13 = this.maxX - x;
        int var15 = this.maxY - y;
        int var17 = this.maxZ - z;
        return createCached(var7, var9, var11, var13, var15, var17);
    }

    public GameBox copy() {
        return createCached(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ);
    }

    public HitResult raycast(Vec3d min, Vec3d max) {
        Vec3d var3 = min.interpolateByX(max, this.minX);
        Vec3d var4 = min.interpolateByX(max, this.maxX);
        Vec3d var5 = min.interpolateByY(max, this.minY);
        Vec3d var6 = min.interpolateByY(max, this.maxY);
        Vec3d var7 = min.interpolateByZ(max, this.minZ);
        Vec3d var8 = min.interpolateByZ(max, this.maxZ);
        if (!this.containsInYZPlane(var3)) {
            var3 = null;
        }

        if (!this.containsInYZPlane(var4)) {
            var4 = null;
        }

        if (!this.containsInXZPlane(var5)) {
            var5 = null;
        }

        if (!this.containsInXZPlane(var6)) {
            var6 = null;
        }

        if (!this.containsInXYPlane(var7)) {
            var7 = null;
        }

        if (!this.containsInXYPlane(var8)) {
            var8 = null;
        }

        Vec3d var9 = null;
        if (var3 != null && (var9 == null || min.squaredDistanceTo(var3) < min.squaredDistanceTo(var9))) {
            var9 = var3;
        }

        if (var4 != null && (var9 == null || min.squaredDistanceTo(var4) < min.squaredDistanceTo(var9))) {
            var9 = var4;
        }

        if (var5 != null && (var9 == null || min.squaredDistanceTo(var5) < min.squaredDistanceTo(var9))) {
            var9 = var5;
        }

        if (var6 != null && (var9 == null || min.squaredDistanceTo(var6) < min.squaredDistanceTo(var9))) {
            var9 = var6;
        }

        if (var7 != null && (var9 == null || min.squaredDistanceTo(var7) < min.squaredDistanceTo(var9))) {
            var9 = var7;
        }

        if (var8 != null && (var9 == null || min.squaredDistanceTo(var8) < min.squaredDistanceTo(var9))) {
            var9 = var8;
        }

        if (var9 == null) {
            return null;
        } else {
            byte var10 = -1;
            if (var9 == var3) {
                var10 = 4;
            }

            if (var9 == var4) {
                var10 = 5;
            }

            if (var9 == var5) {
                var10 = 0;
            }

            if (var9 == var6) {
                var10 = 1;
            }

            if (var9 == var7) {
                var10 = 2;
            }

            if (var9 == var8) {
                var10 = 3;
            }

            return new HitResult(0, 0, 0, var10, var9);
        }
    }

    private boolean containsInYZPlane(Vec3d pos) {
        if (pos == null) {
            return false;
        } else {
            return pos.y >= this.minY && pos.y <= this.maxY && pos.z >= this.minZ && pos.z <= this.maxZ;
        }
    }

    private boolean containsInXZPlane(Vec3d pos) {
        if (pos == null) {
            return false;
        } else {
            return pos.x >= this.minX && pos.x <= this.maxX && pos.z >= this.minZ && pos.z <= this.maxZ;
        }
    }

    private boolean containsInXYPlane(Vec3d pos) {
        if (pos == null) {
            return false;
        } else {
            return pos.x >= this.minX && pos.x <= this.maxX && pos.y >= this.minY && pos.y <= this.maxY;
        }
    }

    public void clone(GameBox other) {
        this.minX = other.minX;
        this.minY = other.minY;
        this.minZ = other.minZ;
        this.maxX = other.maxX;
        this.maxY = other.maxY;
        this.maxZ = other.maxZ;
    }

    public String toString() {
        return "box[" + this.minX + ", " + this.minY + ", " + this.minZ + " -> " + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
    }
}

