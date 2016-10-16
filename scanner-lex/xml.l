%{
int linenum = 1;
%}
leftBracket <
rightBracket >
colon :
question \?
slash \/
letter [a-zA-Z]
digit [0-9]
id {letter}+(-|{letter}|{digit})*
whiteSpace [ \t]+
assign =
string \"[^\"]*\"
newLine \n
%%
{newLine} {++linenum;}
{whiteSpace} {}
{leftBracket} printf("<\n");
{rightBracket} printf(">\n");
{colon} printf(":\n");
{assign} printf("=\n");
{question} printf("?\n");
{slash} printf("/\n");
{string} {
    printf("\"\n");
    yytext[strlen(yytext)-1]='\0';
    printf("%s\n", yytext+1);
    printf("\"\n");
}
{id} printf("%s\n", yytext);
(^\r) {
    fprintf(stderr, "Error at line %d: %s\n", linenum, yytext);
    exit(1);
}
