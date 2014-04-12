.source Test.mj
.class public Test
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 100
.limit locals 100
ldc 5
newarray int
astore 1
aload 1
ldc 3
ldc 2
iastore
iconst_1
istore 2
iconst_0
istore 3
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 2
ifeq l0
ldc "true"
goto l1
l0:
ldc "false"
l1:
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
iload 3
ifeq l2
ldc "true"
goto l3
l2:
ldc "false"
l3:
invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 1
ldc 3
iaload
invokevirtual java/io/PrintStream/println(I)V
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 1
arraylength
invokevirtual java/io/PrintStream/println(I)V
return
.end method
