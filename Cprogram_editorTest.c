#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#define CHAR_IN_LINE 255 /*each line can have 255 characters */
int main(void)
{
	char ar[CHAR_IN_LINE],programName[100];
	FILE *cProgram;
	printf("Welcome to my C program editor \n");
	printf("Give the name of your program (it will be appended with .c automatically ) : ");
	fgets(programName,100,stdin);
    programName[strlen(programName)-1]='\0';
	strcat(programName,".c");
	cProgram=fopen(programName,"w");
	if(cProgram == NULL)
	{
		printf("failure \n");
		exit(EXIT_FAILURE );
	}
	printf("Type /*exit*/ to exit \n");
	while(1)
	{
		fgets(ar,CHAR_IN_LINE,stdin);
		fprintf(cProgram,"%s",ar);
		ar[strlen(ar)-1]='\0';
		if(strcmp(ar,"/*exit*/")==0)
			break;
	}
	fclose(cProgram);
	return 0;
}
