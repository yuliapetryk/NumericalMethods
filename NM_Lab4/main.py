import numpy as np


def calculate_system(x):
    return np.array([
        x[0] ** 2 - 2 * x[0] * x[1] + 1,
        x[0] ** 2 + x[1] ** 2 - 2
    ])


def calculate_jacobian_matrix(x):
    return np.array([
        [2 * x[0] - 2 * x[1], -2 * x[0]],
        [2 * x[0], 2 * x[1]]
    ])


def calculate_system_n(x):
    n = len(x)
    res = np.full(n, np.sum(x**2) - n)
    for i in range(n):
        res[i] -= x[i]**2 - x[i]**3
    return res


def calculate_jacobian_matrix_n(x):
    n = len(x)
    res = np.tile(2 * x, (n, 1))
    for i in range(n):
        res[i][i] *= 1.5 * x[i]

    return res


def newton(system, jacobian_matrix, x, epsilon):
    i = 0
    while True:
        i += 1
        z = np.linalg.solve(jacobian_matrix(x), system(x))
        x -= z
        if np.linalg.norm(z) < epsilon:
            return x, i


def relaxation(system, jacobian_matrix, x, epsilon):
    iter = 10000
    for i in range(iter):
        tau = 2 / np.linalg.norm(jacobian_matrix(x), ord=np.inf) - epsilon
        x1 = x - tau * system(x)
        if (np.linalg.norm(x1 - x) < epsilon):
            return x, i
        x = x1
    return x, i


epsilon = 0.001
result, iterations = newton(calculate_system, calculate_jacobian_matrix, [0.8, 0.8], epsilon)
print("Newton method: \n", result, "\n", iterations, " iterations")

result, iterations = relaxation(calculate_system, calculate_jacobian_matrix, [0.9, 0.9], epsilon)
print("Relaxation method: \n", result, "\n", iterations, " iterations")

n = 10

x = np.full(n, 0.5)
result, iterations = newton(calculate_system_n, calculate_jacobian_matrix_n, x, epsilon)
print("Newton method: \n", result, "\n", iterations, " iterations")

x = np.full(n, 0.5)
result, iterations = relaxation(calculate_system_n, calculate_jacobian_matrix_n, x, epsilon)
print("Relaxation method: \n", result, "\n", iterations, " iterations")

