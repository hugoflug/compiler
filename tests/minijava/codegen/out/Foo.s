.source 'Test.mj'
.class public 'Foo'
.super java/lang/Object
.field public 'a' 'I'
.field public 'b' 'I'
.field public 'c' 'I'
.field public 'foo' 'LFoo;'
.method public <init>()V
.limit stack 1
.limit locals 1
aload_0
invokespecial java/lang/Object/<init>()V
return
.end method
.method public 'foo()I'
.limit stack 3
.limit locals 1
iconst_0
iconst_1
ixor
ifeq l4
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 666
invokevirtual java/io/PrintStream/println(I)V
l4:
ldc 0
ireturn
.end method
.method public 'boo()I'
.limit stack 2
.limit locals 1
iconst_1
ireturn
.end method
.method public 'add(II)I'
.limit stack 3
.limit locals 4
ldc 56
istore 3
iload 1
iload 2
iadd
ireturn
.end method
