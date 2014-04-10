.source Test.mj
.class public Test
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
ldc 3
istore 1
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 5
invokevirtual java/io/PrintStream/println(I)V
iload 1
ldc 2
if_icmplt l0
iconst_0 
goto l1
l0:
iconst_1
l1:
ifeq l2
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 7
invokevirtual java/io/PrintStream/println(I)V
goto l3
l2:
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 1
ldc 1
isub
ldc 3
imul
ldc 5
iadd
invokevirtual java/io/PrintStream/println(I)V
l3:
new Foo
dup
invokespecial Foo/<init>()V
astore 2
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 2
invokevirtual Foo/foo()I
invokevirtual java/io/PrintStream/println(I)V
return
.end method
