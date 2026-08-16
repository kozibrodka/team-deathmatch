package net.kozibrodka.deathmatch.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.FabricLoader;
import net.kozibrodka.deathmatch.events.Deathmatch;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

public class TeamSyncPacket extends Packet implements ManagedPacket<TeamSyncPacket> {

    public static final PacketType<TeamSyncPacket> TYPE = PacketType.builder(true, true, TeamSyncPacket::new).build();

    private String[] redNames;
    private String[] blueNames;

    public TeamSyncPacket() {
    }

    public TeamSyncPacket(Set<String> red, Set<String> blue) {
        this.redNames = red.toArray(new String[0]);
        this.blueNames = blue.toArray(new String[0]);
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.redNames = readStringArray(stream);
            this.blueNames = readStringArray(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        try {
            writeStringArray(stream, redNames);
            writeStringArray(stream, blueNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeStringArray(DataOutputStream stream, String[] arr) throws IOException {
        stream.writeInt(arr.length);
        for (String s : arr) {
            stream.writeUTF(s);
        }
    }

    private String[] readStringArray(DataInputStream stream) throws IOException {
        int len = stream.readInt();
        String[] arr = new String[len];
        for (int i = 0; i < len; i++) {
            arr[i] = stream.readUTF();
        }
        return arr;
    }

    @Override
    public void apply(NetworkHandler arg) {
        switch (FabricLoader.INSTANCE.getEnvironmentType()) {
            case CLIENT -> handleClient(arg);
            case SERVER -> handleServer(arg);
        }
    }

    @Environment(EnvType.CLIENT)
    public void handleClient(NetworkHandler networkHandler) {
        Deathmatch.TEAM_RED.clear();
        Deathmatch.TEAM_RED.addAll(Arrays.asList(redNames));

        Deathmatch.TEAM_BLUE.clear();
        Deathmatch.TEAM_BLUE.addAll(Arrays.asList(blueNames));
    }

    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler) {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public @NotNull PacketType<TeamSyncPacket> getType() {
        return TYPE;
    }
}
