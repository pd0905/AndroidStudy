/*
��Ȩ���� (c) 2019, ��������˫�Ƽ����޹�˾
��������Ȩ��.

V 1.0
����: tianshuang

����: JGlobalKeyIF

��ʷ: 1. 2019/01/19, ��������
      2. 2019/04/28, ����GetFloatData
	  3. 2019/05/31, ����HS532��ؽӿںͺ���
*/

package tianshuang.globalkey;
import tianshuang.globalkey.JGlobalKeyIFEventListener;

public class JGlobalKeyIF
{
	static
	{
		System.loadLibrary("globalkeyif_jni");
	}

	//��Ҫ���ò���
	public static class KeyConfig
	{
		public boolean	m_bIfRecord;			//�Ƿ�¼��
		public String	m_strRecordPath;		//¼��·��
		public int		m_iRecordPackTime;		//¼����ʱ��
		public int		m_iReversingTime;		//������ʱʱ��
		public boolean	m_bIfSteerStartGlobal;	//ת��ʱ�Ƿ���ȫ���������٣�
		public boolean	m_bIfBootStartGlobal;	//�������Ƿ���ȫ��
		public boolean	m_bIfLDWS;				//�Ƿ�������ƫ��
		public int		m_iLDWSStartSpeed;		//����ƫ�뿪���ٶ�
		public boolean	m_bIfBSDS;				//�Ƿ���ä������
		public int		m_iBSDSStartSpeed;		//ä�����������ٶ�
		public boolean	m_bIfBSDSScreen;		//�Ƿ�����Ļ����ʾä������
		public boolean	m_bIfBSDSSound;			//�Ƿ񲥷�ä��������ʾ��
		public int		m_iSpeedCtrlParam1;		//���ٿ��Ʋ���1�������������ѣ�
	}

	//��Ҫ���ò���
	public static class SecConfig
	{
		public boolean	m_bIfMODS;				//�Ƿ����ƶ����
		public boolean	m_bIfAPA;				//�Ƿ����Զ�����
		public int		m_iMODSSpeed;			//�ƶ�����ٶȲ���
		public int		m_iAPASpeed;			//�Զ������ٶȲ���
		public boolean	m_bIfDOW;				//�Ƿ�������Ԥ��
		public int		m_iDOWSpeed;			//����Ԥ���ٶȲ���
		public boolean	m_bFastAutoExit;		//�����Ƿ��Զ��˳�ȫ��
		public int		m_iAutExitSpeed;		//�Զ��˳�ȫ���ٶ�
	}

	//��ʢUI����ɽ��ʢ�����ò���
	public static class HSUIConfig
	{
		public boolean	m_bIfDrivingStaticTrace;		//�Ƿ���������̬�켣�ߣ��ݳ�����ģʽ��ʾ�켣�ߣ�
		public boolean	m_bIfDrivingDynamicTrace;		//�Ƿ���������̬�켣�ߣ�����ͼ��ʾ���������ߣ�
		public boolean	m_bIfDrivingMergerTrace;		//�Ƿ���ƴ������켣�ߣ�ȫ��ͼ��ʾ���������ߣ�
		public boolean	m_bIfRadar;						//�Ƿ����״���ʾ����ʾ�ϰ��ﾯ�棩
		public boolean	m_bIfBVM;						//�Ƿ���BVM��ʾ������ä�����ϵͳ��
		public boolean	m_bIfBVMTrace;					//�Ƿ���BVM�켣��ä�������Ƶ��ʾ�����ߣ�
		public int		m_iDefaultMode;					//ǰģʽ��0ǰ��ͼ��1ǰ�����ͼ��2��ǰ��ͼ��3��ǰ��ͼ��4������ͼȫ��
		public int		m_iDefaultModeB;				//��ģʽ��0�����ͼ��1�Һ���ͼ��2������ͼȫ��
		public boolean	m_bIfReserve1;					//Ԥ��
		public boolean	m_bIfReserve2;					//Ԥ��
		public boolean	m_bIfReserve3;					//Ԥ��
		public boolean	m_bIfReserve4;					//Ԥ��
		public int		m_iReserve1;					//Ԥ��
		public int		m_iReserve2;					//Ԥ��
		public int		m_iReserve3;					//Ԥ��
		public int		m_iReserve4;					//Ԥ��
	}

	//HS591�������
	public static class HS591Config
	{
		public boolean	m_bIfLeftSteerStartGlobal;	//��ת��ʱ�Ƿ���ȫ��
		public boolean	m_bIfRightSteerStartGlobal;	//��ת��ʱ�Ƿ���ȫ��
		public boolean  m_bIfRadarStartGlobal;      //�״��⵽�ź��Ƿ���ȫ��
		public boolean  m_bIfWheelSteeringStartGlobal; //������ת���Ƿ���ȫ��
		
		public boolean	m_bIfDrivingStaticTrace;		//�Ƿ���������̬�켣�ߣ��ݳ�����ģʽ��ʾ�켣�ߣ�
		public boolean	m_bIfDrivingDynamicTrace;		//�Ƿ���������̬�켣�ߣ�ǰ��ͼ��ʾ���������ߣ�
		
		public boolean	m_bIfAdvanceStaticTrace;		//�Ƿ���ǰ����̬�켣�ߣ��ݳ�����ģʽ��ʾ�켣�ߣ�
		public boolean	m_bIfAdvanceDynamicTrace;		//�Ƿ���ǰ����̬�켣�ߣ�ǰ��ͼ��ʾ���������ߣ�
	}
	
	//HS532�������
	public static class HS532Config
	{
		public boolean	m_bIfSteerStartGlobal;	        //ת��ʱ�Ƿ���ȫ��
		public boolean	m_bIfRadarStartGlobal;          //�״��⵽�ź��Ƿ���ȫ��
		public boolean	m_bIfWheelSteeringStartGlobal;  //������ת���Ƿ���ȫ��
	 
		public boolean	m_bIfMODS;				        //�Ƿ����ƶ����
		public int		m_iMODSSpeed;			 		//�ƶ�����ٶȲ���
		public int 		m_iUIType ;							//UI���
		public int 		m_iCaliLineType;					//�����궨�߱�
	}
	
	//���ü����ص�����
	//listener��������		iParam���������� 1apk���� 2client���� 3any
	//����0
	public native int SetListener(JGlobalKeyIFEventListener listener,int iParam);

	// ���ø�������
	// �ο�Cͷ�ļ�TSH_GlobalKeyIF_SetFloatData
	public native void SetFloatData(int iType,float fValue);

	// ���ܣ���ȡ�������ݣ���ȡͨ��SetFloatData���õĸ�������
	// ���ظ�������
	public native float GetFloatData(int iType);
	
	// �����������ݣ�ע�����ݵĴ��䷽��
	// �ο�Cͷ�ļ�TSH_GlobalKeyIF_SetIntData
	public native void SetIntData(int iType,int iValue);

	// ��ȡ���ݻ���ִ������
	// �ο�Cͷ�ļ�TSH_GlobalKeyIF_ExecCmd
	public native int ExecCmd(int iCmd,String strValue);

	// ���ܣ�����ȫ������
	// ����0
	public native int SetConfig(KeyConfig config);

	// ���ܣ���ȡȫ������
	// ����0
	public native int GetConfig(KeyConfig config);

	// ���ܣ����ô�Ҫ����
	// ����0
	public native int SetSecConfig(SecConfig config);

	// ���ܣ���ȡ��Ҫ����
	// ����0
	public native int GetSecConfig(SecConfig config);

	// ���ܣ�����HSUI����
	// ����0
	public native int SetHSUIConfig(HSUIConfig config);

	// ���ܣ���ȡHSUI����
	// ����0
	public native int GetHSUIConfig(HSUIConfig config);
	
	// ���ܣ�����HS591����
	// ����0
	public native int SetHS591Config(HS591Config config);
	
	// ���ܣ���ȡHS591����
	// ����0
	public native int GetHS591Config(HS591Config config);
	
	// ���ܣ�����HS532����
	// ����0
	public native int SetHS532Config(HS532Config config);
	
	// ���ܣ���ȡHS532����
	// ����0
	public native int GetHS532Config(HS532Config config);
	
}

