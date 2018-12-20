# coding=utf-8
import numpy as np
import matplotlib.pyplot as plt


def dist(v_A, v_B):
    """
    判断两个节点之间的一维距离
    :param v_A: A 二维向量
    :param v_B: B 二维向量
    :return: 一维距离
    """
    return np.sqrt(np.power((v_A[0] - v_B[0]), 2) + np.power((v_A[1] - v_B[1]), 2))


def node_factory(N):
    """
    生成N个节点的集合
    :param N: 节点的数目
    :param nodes: 节点的集合
    :param selected_flag: 标志:是否被选择为簇首-->初始化为0
    :return: 节点集合nodes=[[x,y],[x,y]...] + 标志falg
    """
    nodes = []
    selected_flag = []
    for i in range(0, N):
        # 在1*1矩阵生成[x,y]坐标
        node = [np.random.random(), np.random.random()]
        # print("生成的节点为:", node)
        nodes.append(node)
        # 对应的选择标志初始化为0
        selected_flag.append(0)

    # print("生成:", len(nodes), "个节点")
    # print("初始化标志列表为", selected_flag)
    return nodes, selected_flag


def sel_heads(r, nodes, flags):
    """
    根据阈值选取簇头节点
    :param r: 轮数
    :param nodes: 节点列表
    :param flags: 选择标志
    :param P: 比例因子
    :return: 簇首列表heads,簇成员列表members
    """
    # 阈值函数 Tn 使用leach计算
    P = 0.05 * (100 / len(nodes))
    Tn = P / (1 - P * (r % (1 / P)))
    # print("阈值为:", Tn)

    # 簇首列表
    heads = []
    # 簇成员列表
    members = []
    # 本轮簇首数
    n_head = 0

    # 对每个节点生成对应的随机数
    rands = [np.random.random() for _ in range(len(nodes))]
    # print("随机数为:", rands)

    # 遍历随机数列表，选取簇首
    for i in range(len(nodes)):
        # 若此节点未被选择为簇首
        if flags[i] == 0:
            # 随机数低于阈值-->选为簇首
            if rands[i] <= Tn:
                flags[i] = 1
                heads.append(nodes[i])
                n_head += 1
                # print(nodes[i], "被选为第", n_head, "个簇首")
            # 随机数高于阈值
            else:
                members.append(nodes[i])
        # 若此节点已经被选择过
        else:
            members.append(nodes[i])

    print("簇首为:", len(heads), "个")
    print("簇成员为:", len(members), "个")

    return heads, members


def claasify(nodes, flag, k=1):
    """
    进行簇分类
    :param nodes: 节点列表
    :param flag: 节点标记
    :param k: 轮数
    :return: 簇分类结果列表 classes[[类1..],[类2...],......]  [类1...簇首...簇成员]
    """
    # k轮的集合
    global head_cla
    iter_classes = []
    # 迭代r轮
    for r in range(k):
        # 获取簇首列表，簇成员列表
        heads, members = sel_heads(r, nodes, flag)

        # 建立簇类的列表
        classes = [[] for _ in range(len(heads))]

        # 将簇首作为首节点添加到聚类列表中
        for i in range(len(heads)):
            # print("第", i + 1, "个簇首为", heads[i])
            classes[i].append(heads[i])

        # print("簇首集合:", classes)

        # 簇分类:遍历节点node
        for n in range(len(members)):

            # 选取距离最小的节点
            dist_min = 1

            for i in range(len(heads)):
                dist_heads = dist(members[n], heads[i])

                # 找到距离最小的簇头对应的heads下标i
                if dist_heads < dist_min:
                    dist_min = dist_heads
                    head_cla = i
            # 0个簇首的情况
            if dist_min == 1:
                print("本轮没有簇首!")
                break

            # 添加到距离最小的簇首对应的聚类列表中
            classes[head_cla].append(members[n])

        # 将簇首作为首节点添加到聚类列表中
        # for i in range(len(classes)):
        # print("第", i + 1, "类包含:", classes[i])

        iter_classes.append(classes)

    return iter_classes


def show_plt(classes):
    """
    显示分类图
    :param classes: [[类1...],[类2...]....]-->[簇首,成员,成员...]
    :return:
    """
    fig = plt.figure()
    ax1 = plt.gca()

    # 设置标题
    ax1.set_title('WSN1')
    # 设置X轴标签
    plt.xlabel('X')
    # 设置Y轴标签
    plt.ylabel('Y')

    icon = ['o', '*', '.', 'x', '+', 's']
    color = ['r', 'b', 'g', 'c', 'y', 'm']

    # 对每个簇分类列表进行show
    for i in range(len(classes)):
        centor = classes[i][0]
        # print("第", i + 1, "类聚类中心为:", centor)
        for point in classes[i]:
            ax1.plot([centor[0], point[0]], [centor[1], point[1]], c=color[i % 6], marker=icon[i % 5], alpha=0.4)

    # 显示所画的图
    plt.show()


def run():
    """
    1、输入节点个数N
    2、node_factory(N): 生成N个节点的列表
    3、classify(nodes,flag,k=10): 进行k轮簇分类，flag已标记的节点不再成为簇首，返回所有簇的列表
    4、show_plt(classes): 迭代每次聚类结果，显示连线图

    :return:
    """
    N = 100
    # N = int(input("请输入节点个数:"))
    # 获取初始节点列表，选择标志列表
    nodes, flag = node_factory(N)
    # 对节点列表进行簇分类，k为迭代轮数
    iter_classes = claasify(nodes, flag, k=10)

    for classes in iter_classes:
        # 显示分类结果
        show_plt(classes)


if __name__ == '__main__':
    run()
