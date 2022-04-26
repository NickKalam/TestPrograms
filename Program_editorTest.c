#include<stdio.h>
#include<string.h>
#include<unistd.h>
#include<stdlib.h>
#define CHAR_IN_LINE 255 /*each line can have 255 characters */
void pickLang(char extens[]);
int main(void)
{
	char ar[CHAR_IN_LINE],programName[100],extens[6];
	FILE *Program;
	
	printf("Welcome to my  program editor \n");
	sleep(3);
	system("clear");
	pickLang(extens);/*call function to pick the programming language */
	
	printf("Give the name of your program (it will be appended with %s automatically ) : ",extens);
	fgets(programName,100,stdin);
        programName[strlen(programName)-1]='\0';
	strcat(programName,extens);

	Program=fopen(programName,"w");
	if(Program == NULL)
	{
		printf("failure \n");
		exit(EXIT_FAILURE );
	}
	system("clear");
	
	printf("%s\n",programName);
	printf("Type /*exit*/ to exit \n");
	while(1)
	{
		fgets(ar,CHAR_IN_LINE,stdin);
		if(strcmp(ar,"/*exit*/\n")==0)
			break;
		fprintf(Program,"%s",ar);

	}
	System("clear");
	
	showFile(Program);
	fclose(Program);
	return 0;
}

void pickLang(char extens[])
{
	char lang;
	
	while(1)
	{
		printf("Pick the language of the program : \n1.C \n2.C++ \n3.Java \n4.Pascal \n");
		scanf("%c",&lang);
		getchar();
		system("clear");
		
		switch(lang)
		{
			case '1' :
				strcpy(extens,".c");
			return ;

			case '2' :
				strcpy(extens,".cpp");
			return ;

			case '3':
				strcpy(extens,".java");
			return ;

			case '4' :
				strcpy(extens,".pas");
			return ;
		}
	}	
}

void showFile(FILE *fp)
{
	rewind(fp);
	char c;
	c=fgetc(fp);
	while(c!=EOF)
	{
		printf("%c",c);
		c=fgetc(fp);
	}
}
