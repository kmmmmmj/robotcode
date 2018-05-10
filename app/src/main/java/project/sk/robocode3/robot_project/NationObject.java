package project.sk.robocode3.robot_project;

public class NationObject {

    static String[] nation_name = new String[]{"한국","일본","중국","미국"};
    static String[] nation_capital = new String[]{"서울","도쿄","베이징","워싱턴"};
    static int[] nation_oid = new int[]{51814,51813,51811,51815};
    //static int[] nation_flag = new int[]{R.drawable.korea,R.drawable.japan,R.drawable.china,R.drawable.america};
    //static int[] capital_button = new int[]{R.drawable.seoul,R.drawable.tokyo,R.drawable.beijing,R.drawable.washington};

    //기본정보
    public int index;       //나라인덱스
    public String name;     //나라이름
    public String dae;      //대륙이름
    public String cho;      //초성
    public int oid;         //오아이디
    public String color;    //카드색
    public int flagImage;   //국기 Drawble

    //퀴즈
    public int capitalIndex;
    public int clothesIndex;
    public int moneyIndex;
    public int foodIndex;

    //방문횟수
    public boolean visitNum;

    //대륙정보
    //public String[] = {"아시아", "유럽", "오세아니아", "아프리카", "북아메리카", "남아메리카"};

    public NationObject(int index, String name, String dae, String cho, int oid, String color, int flagImage){
        this.index = index;
        this.name = name;
        this.dae = dae;
        this.cho = cho;
        this.oid = oid;
        this.color = color;
        this.flagImage = flagImage;

    }
    public void InitNation(){



    }


}
