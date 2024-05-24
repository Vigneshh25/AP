//#include<stdio.h>
//        #include<conio.h>
//        void main()
//        {
//        int i,k,j,t,m,n,u,count;
//        char str[100][500],ch;
//        clrscr();
//        scanf("%d",&t);
//        while(t--)
//        {
//        scanf("%d",&m);
//        scanf("\n%c",&ch);
//        scanf("%d",&n);
//        for(i=0;i<n;i++)
//        scanf("%s",str[i]);
//        for(i=0;i<n;i++)
//        {
//        if(str[i][0]!='\0')
//        {
//        count=0;
//        printf("%s ",str[i]);
//        for(j=0;str[i][j]!='\0';j++)
//        if(str[i][j]!=ch)
//        count++;
//        for(j=i+1;j<n;j++)
//        {
//        if(str[j][0]!='\0')
//        {
//        u=0;
//        for(k=0;str[j][k]!='\0';k++)
//        if(str[j][k]!=ch)
//        u++;
//        if(u+count<=m)
//        {
//        count=count+u;
//        printf("%s ",str[j]);
//        str[j][0]='\0';
//        }
////printf("\n%d %d %d %d\n",i,j,count,u);
//        if(count>=m)
//        break;
////printf("\n||%d %d %d %d||\n",i,j,count,u);
//        }
//        }
//        }
//        if(str[i][0]!='\0')
//        printf("\n");
//        }
//        }
//
//        getch();}