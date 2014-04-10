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
getstatic java/lang/System/out Ljava/io/PrintStream;
aload 1
ldc 3
iaload
aload 1
ldc 3
iaload
invokevirtual java/io/PrintStream/println(I)V
return
.end method
