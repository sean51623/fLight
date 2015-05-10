import java.util.*;
import java.net.*;

public class Bfclient_packet123{
	InetAddress m_dstAddr;
    int         m_dstPort;
    InetAddress m_srcAddr;
    int         m_srcPort;
    byte[]      m_userData;

    public bfclient_packet (byte[] rawPacket) {
        
		/*
        try {
            byte[] destRawAddr = new byte[4];
            byte[] destRawPort = new byte[4];
            byte[] srcRawAddr  = new byte[4];
            byte[] srcRawPort  = new byte[4];
            
            System.arraycopy (rawPacket,   0, destRawAddr, 0, 4);
            System.arraycopy (rawPacket,   4, destRawPort, 0, 4);
            System.arraycopy (rawPacket,   8, srcRawAddr,  0, 4);
            System.arraycopy (rawPacket,  12, srcRawPort,  0, 4);

            m_dstAddr = InetAddress.getByAddress (destRawAddr);
            m_dstPort = ByteBuffer.wrap (destRawPort).getInt ();
            m_srcAddr = InetAddress.getByAddress (srcRawAddr);
            m_srcPort = ByteBuffer.wrap (srcRawPort).getInt ();
            
            if (m_dataLen != 0) {
                m_userData = new byte [m_dataLen];
                System.arraycopy (rawPacket,  24, m_userData,  0, m_dataLen);
            } else {
                m_userData = null;
            }
            
        } catch (Exception e) {
            bfclient.logExp (e, false);
        }
		*/
    }
	
    public byte[] pack () {
        byte[] destRawAddr = m_dstAddr.getAddress ();
        byte[] destRawPort = ByteBuffer.allocate(4).putInt(m_dstPort).array ();
        byte[] srcRawAddr  = m_srcAddr.getAddress ();
        byte[] srcRawPort  = ByteBuffer.allocate(4).putInt(m_srcPort).array ();
        
        byte[] packet = new byte[packetTotalLen];
        
        System.arraycopy (destRawAddr, 0, packet,  0, destRawAddr.length);
        System.arraycopy (destRawPort, 0, packet,  4, destRawPort.length);
        System.arraycopy (srcRawAddr,  0, packet,  8, srcRawAddr.length);
        System.arraycopy (srcRawPort,  0, packet, 12, srcRawPort.length);

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream outputStream = new ObjectOutputStream(out);
		outputStream.writeObject(si);
		outputStream.close();
		
        if (rawData != null)
            System.arraycopy (rawData,  0, packet, 24, rawData.length);
        
        return packet;
    }
}
