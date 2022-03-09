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
	fclose(Program);
	return 0;
}

void pickLang(char extens[])
{
	char lang;
	do
	{
		printf("Pick the language of the program : \n1.C \n2.C++ \n3.Java \n4.Pascal \n");
		scanf("%c",&lang);
		getchar();
		system("clear");
	}while(lang!='1' && lang!='2' && lang!='3' && lang!='4');

	switch(lang)
	{
		case '1' :
			strcpy(extens,".c");
		break;

		case '2' :
			strcpy(extens,".cpp");
		break;

		case '3':
			strcpy(extens,".java");
		break;

		default :/*if the user picked  pascal */
			strcpy(extens,".pas");
		break;
	}
	
}
