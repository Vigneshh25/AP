package set1;

public class SearchString {
        public static void main(String[] args) {
            String ss = "WELCOMETOZOHOCORPORATION ";
            String s =ss.toLowerCase();
            char[][] str = new char[5][5];
            int q=0;
            for (int i=0;i<5;i++)
            {
                for(int j=0;j<5;j++)
                {
                    str[i][j] = s.charAt(q++);
                }
            }
            for (int i=0;i<5;i++)
            {
                for(int j=0;j<5;j++)
                {
                    System.out.print(str[i][j]+" ");
                }
                System.out.println();
            }

            for (int i=0;i<5-1;i++)
            {
                for(int j=0;j<5-1;j++)
                {
                    if(str[i][j]=='t'&&str[i][j+1]=='o'&&str[i][j+2]=='o')
                    {
                        System.out.println("Start "+i+" "+j);
                        System.out.println("End "+i+" "+j+2);
                    }
                    if(str[i][j]=='t'&&str[i+1][j]=='o'&&str[i+2][j]=='o')
                    {
                        System.out.println("Start "+i+" "+j);
                        System.out.println("End "+(i+2)+" "+j);
                    }
                }
            }

        }
    }

