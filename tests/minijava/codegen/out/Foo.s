.source Test.mj
.class public Foo
.super java/lang/Object
.field public a I
.field public b I
.method public <init>()V
.limit stack 100
.limit locals 100
aload_0
invokespecial java/lang/Object/<init>()V
return
.end method
.method public foo()I
.limit stack 100
.limit locals 100
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 78
invokevirtual java/io/PrintStream/println(I)V
ldc 4
aload_0
swap
putfield Foo/a I
ldc 5
aload_0
swap
putfield Foo/b I
aload_0
invokevirtual Foo/boo()I
aload_0
getfield Foo/b I
aload_0
getfield Foo/a I
if_icmpgt l0
iconst_0 
goto l1
l0:
iconst_1
l1:
iand
ifeq l2
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 999
invokevirtual java/io/PrintStream/println(I)V
ldc 77
aload_0
swap
putfield Foo/a I
l2:
getstatic java/lang/System/out Ljava/io/PrintStream;
aload_0
getfield Foo/a I
invokevirtual java/io/PrintStream/println(I)V
ldc 5
ireturn
.end method
.method public boo()I
.limit stack 100
.limit locals 100
iconst_1
ireturn
.end method
